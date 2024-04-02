package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.ApplicationModel;
import com.example.beyondcurrency.models.ServiceModel;
import com.example.beyondcurrency.models.UserApplicationModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.ApplicationsRepository;
import com.example.beyondcurrency.repositories.NotificationRepository;
import com.example.beyondcurrency.repositories.PostRepository;
import com.example.beyondcurrency.repositories.UserLoginRegistrationRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {
    @Resource
    PostRepository postRepository;
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;
    @Resource
    ApplicationsRepository applicantsRepository;
    @Resource
    NotificationRepository notificationRepository;

    @GetMapping("/post/{id}")
    public String displayPost(Model model, HttpSession session, @PathVariable(name = "id") Integer id){

        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        ServiceModel post = postRepository.getPostByServiceId(id);
        UserModel poster = userLoginRegistrationRepository.getUserById(post.getPosterId());
        UserModel taker = userLoginRegistrationRepository.getUserById(post.getTakerId());

        model.addAttribute("post", post);
        model.addAttribute("poster", poster);
        if(taker != null) {
            model.addAttribute("taker", taker);
        }
        //sent description content to a list
        String description = post.getDescription();
        String[] paragraphs = description.split("\\r?\\n");
        List<String> paragraphList = Arrays.asList(paragraphs);
        model.addAttribute("paragraphList", paragraphList);

        //select related applications (when the logged-in user is the poster of the post)
        List<ApplicationModel> applications = applicantsRepository.getAllApplications();
        List<UserApplicationModel> relatedApp = new ArrayList<>();
        for(ApplicationModel a : applications) {
            if (a.getServiceId() == post.getServiceId() && a.getStatus().equals("pending")){
                UserModel user = userLoginRegistrationRepository.getUserById(a.getApplicantId());
                UserApplicationModel userApplication = new UserApplicationModel(a.getApplicationId(), a.getApplicantId(), a.getServiceId(), a.getPosterId(),a.getStatus(), user.getImageUrl(), user.getFirstName(), user.getLastName(), poster.getSkill1(),poster.getSkill2(),poster.getSkill3());
                relatedApp.add(userApplication);
            }
        }
        model.addAttribute("relatedApp", relatedApp);

        //check if logged-in user already applied (when the logged-in user is not the poster of the post)
        Boolean isApplied = false;
        for(ApplicationModel a : applications) {
            if (post.getServiceId() == a.getServiceId() && loginUser.getUserId() == a.getApplicantId()){
                isApplied = true;
            }
        }
        model.addAttribute("isApplied", isApplied);



//        System.out.println(loginUser.getUserId());
        if (loginUser != null) {
            if (post.getPosterId() == loginUser.getUserId()) {
                // Logic for when the logged-in user is the poster of the post
                if(post.getStatus().equals("open")) {
                    return "post_poster_view";
                } else if (post.getStatus().equals("filled")){
                    return "post_processing";
                } else if (post.getStatus().equals("closed")){
                    return "post_completed";
                }
            } else if (post.getPosterId() != loginUser.getUserId()){
                // Logic for when the logged-in user is not the poster of the post
                if (post.getTakerId() == loginUser.getUserId()) {
                    // Logic for when the logged-in user is the taker of the post
                    if(post.getStatus().equals("open")) {
                        model.addAttribute("post", post);
                        model.addAttribute("poster", poster);
                        return "post";
                    } else if (post.getStatus().equals("filled")){
                        return "post_processing";
                    } else if (post.getStatus().equals("closed")){
                        return "post_completed";
                    }
                } else {
                    // Logic for when the logged-in user is not the poster or the taker of the post
                    if (post.getStatus().equals("open")){
                        return "post";
                    } else {
                        return "post_completed";
                    }
                }
            }
        } else {
            // Logic for when there's no logged-in user
            return "post_completed";
        }
        return null;
    }

    @GetMapping("/post_poster_view")
    public String displayApplicant(Model model){

        return "post_poster_view";
    }

    @PostMapping("/post_processing")
    public String acceptApplication(@RequestParam("selectedApplicantId") int selectedApplicantId, @RequestParam("exchangeSkill") String exchangeSkill, @RequestParam("post_id") int postId, Model model){
        // update related applications status
        List<ApplicationModel> allApplications = applicantsRepository.getAllApplications();
        for (ApplicationModel a: allApplications) {
            if(a.getApplicantId()==selectedApplicantId && a.getServiceId() == postId){
                applicantsRepository.updateApplicationStatus(a.getApplicationId(), postId, "accepted");
            } else if(a.getApplicantId()!=selectedApplicantId && a.getServiceId() == postId){
                applicantsRepository.updateApplicationStatus(a.getApplicationId(), postId, "rejected");
            }
        }

        // link selected applicant to the post
        ServiceModel post = postRepository.getPostByServiceId(postId);
        postRepository.updateTakerToPost(post, selectedApplicantId, exchangeSkill, "filled");
        ServiceModel updatedPost = postRepository.getPostByServiceId(postId);
        UserModel poster = userLoginRegistrationRepository.getUserById(updatedPost.getPosterId());
        UserModel taker = userLoginRegistrationRepository.getUserById(updatedPost.getTakerId());
        model.addAttribute("post", updatedPost);
        model.addAttribute("poster", poster);
        model.addAttribute("taker", taker);
        return "post_processing";
    }

    @GetMapping("/post_edit")
    public String displayEditPost(Model model){
        return "post_edit";
    }

    @GetMapping("/post_new")
    public String displayNewPost(Model model){
        return "post_new";
    }


    @PostMapping("/post_completed")
    public String displayCompletedPost(Model model){
        return "post_completed";
    }
}

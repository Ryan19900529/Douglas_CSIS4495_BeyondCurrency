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

        //check if the user already rate the service
        Integer rateToPoster = post.getRateToPoster();
        Integer rateToTaker = post.getRateToTaker();

        if (loginUser != null) {
            if (post.getPosterId() == loginUser.getUserId()) {
                // Logic for when the logged-in user is the poster of the post
                if(post.getStatus().equals("open")) {
                    return "post_poster_view";
                } else if (post.getStatus().equals("filled") && rateToTaker == 0){
                    return "post_processing";
                } else {
                    return "post_completed";
                }
            } else if (post.getPosterId() != loginUser.getUserId()){
                // Logic for when the logged-in user is not the poster of the post
                if (post.getTakerId() == loginUser.getUserId()) {
                    // Logic for when the logged-in user is the taker of the post
                    if(post.getStatus().equals("open")) {
                        return "post";
                    } else if (post.getStatus().equals("filled") && rateToPoster == 0){
                        return "post_processing";
                    } else {
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

        //sent description content to a list
        String description = updatedPost.getDescription();
        String[] paragraphs = description.split("\\r?\\n");
        List<String> paragraphList = Arrays.asList(paragraphs);
        model.addAttribute("paragraphList", paragraphList);

        model.addAttribute("post", updatedPost);
        model.addAttribute("poster", poster);
        model.addAttribute("taker", taker);
        return "post_processing";
    }

    @PostMapping("/post_completed")
    public String displayCompletedPost(@RequestParam("post_id") int postId, @RequestParam("taker_id") int takerId, @RequestParam("poster_id") int posterId, @RequestParam("rate") int rate, @RequestParam("comment") String comment, Model model, HttpSession session){
        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        ServiceModel post = postRepository.getPostByServiceId(postId);
        UserModel poster = userLoginRegistrationRepository.getUserById(post.getPosterId());
        UserModel taker = userLoginRegistrationRepository.getUserById(post.getTakerId());

        // update rate and comment
        if(loginUser.getUserId() == posterId) {
            postRepository.updatePosterRateToTaker(post, comment, rate);
            int modifiedScore = modifyTrustScore(rate, taker.getTrustScore());
            userLoginRegistrationRepository.updateUserTrustScore(taker, modifiedScore, taker.getWork_done()+1);


        } else if(loginUser.getUserId() == takerId) {
            postRepository.updateTakerRateToPoster(post, comment, rate);
            int modifiedScore = modifyTrustScore(rate, poster.getTrustScore());
            userLoginRegistrationRepository.updateUserTrustScore(poster, modifiedScore, poster.getWork_done()+1);
        }


        // update post status
        ServiceModel updatedPost1 = postRepository.getPostByServiceId(postId);
        Integer rateToPoster = updatedPost1.getRateToPoster();
        Integer rateToTaker = updatedPost1.getRateToTaker();
        if (rateToPoster != 0 && rateToTaker != 0) {
            postRepository.updatePostStatus(updatedPost1, "closed");
        }

        ServiceModel updatedPost2 = postRepository.getPostByServiceId(postId);
        UserModel updatedPoster = userLoginRegistrationRepository.getUserById(updatedPost2.getPosterId());
        UserModel updatedTaker = userLoginRegistrationRepository.getUserById(updatedPost2.getTakerId());
        //sent description content to a list
        String description = updatedPost2.getDescription();
        String[] paragraphs = description.split("\\r?\\n");
        List<String> paragraphList = Arrays.asList(paragraphs);
        model.addAttribute("paragraphList", paragraphList);

        model.addAttribute("post", updatedPost2);
        model.addAttribute("poster", updatedPoster);
        model.addAttribute("taker", updatedTaker);
        return "post_completed";
    }

    public int modifyTrustScore(int rate, int trustScore){
        int score = trustScore;
        switch (rate) {
            case 1:
                score -= 8;
                break;
            case 2:
                score -= 4;
                break;
            case 3:
                score -= 3;
                break;
            case 4:
                score -= 2;
                break;
            case 5:
                score -= 1;
                break;
            case 7:
                score += 1;
                break;
            case 8:
                score += 2;
                break;
            case 9:
                score += 3;
                break;
            case 10:
                score += 4;
                break;
            default:
                break;
        }

        return score;
    }

    @GetMapping("/post_edit")
    public String displayEditPost(Model model){
        return "post_edit";
    }

    @GetMapping("/post_new")
    public String displayNewPost(Model model){
        return "post_new";
    }

}

package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.AppConfig;
import com.example.beyondcurrency.models.*;
import com.example.beyondcurrency.repositories.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    RequestsRepository requestsRepository;
    @Resource
    NotificationRepository notificationRepository;
    @Resource
    ApplicationsRepository applicationsRepository;

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

        //get notifications for user
        boolean isNewNotification = false;
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        List<NotificationModel> relatedNotifications = new ArrayList<>();
        for (NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUser.getUserId() && n.isShowNotification() == true) {
                relatedNotifications.add(n);
            }
            if(n.getUserId() == loginUser.getUserId() && n.isNewNotification() == true){
                isNewNotification = true;
            }
        }
        model.addAttribute("isNewNotification", isNewNotification);
        model.addAttribute("relatedNotifications", relatedNotifications);

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
    public String acceptApplication(@RequestParam("selectedApplicantId") int selectedApplicantId, @RequestParam("exchangeSkill") String exchangeSkill, @RequestParam("post_id") int postId, Model model, HttpSession session){
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


        // create a new notification
        UserModel applicant = userLoginRegistrationRepository.getUserById(selectedApplicantId);
        NotificationModel notification = new NotificationModel();
        notification.setUserId(applicant.getUserId());
        notification.setSenderId(post.getPosterId());
        notification.setSenderImg(poster.getImageUrl());
        notification.setServiceId(post.getServiceId());
        String content = "Your application to \"" + post.getServiceTitle() + "\" was accepted!";
        notification.setContent(content);
        notificationRepository.addNewNotification(notification);

        model.addAttribute("post", updatedPost);
        model.addAttribute("poster", poster);
        model.addAttribute("taker", taker);

        //get notifications for user
        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        boolean isNewNotification = false;
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        List<NotificationModel> relatedNotifications = new ArrayList<>();
        for (NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUser.getUserId() && n.isShowNotification() == true) {
                relatedNotifications.add(n);
            }
            if(n.getUserId() == loginUser.getUserId() && n.isNewNotification() == true){
                isNewNotification = true;
            }
        }
        model.addAttribute("isNewNotification", isNewNotification);
        model.addAttribute("relatedNotifications", relatedNotifications);
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

        //get notifications for user
        boolean isNewNotification = false;
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        List<NotificationModel> relatedNotifications = new ArrayList<>();
        for (NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUser.getUserId() && n.isShowNotification() == true) {
                relatedNotifications.add(n);
            }
            if(n.getUserId() == loginUser.getUserId() && n.isNewNotification() == true){
                isNewNotification = true;
            }
        }
        model.addAttribute("isNewNotification", isNewNotification);
        model.addAttribute("relatedNotifications", relatedNotifications);
        return "post_completed";
    }

    @PostMapping("/add_new_post")
    public String addNewPost(Model model, HttpSession session, @RequestParam("skillSelected") String skillSelected, @RequestParam("title") String title, @RequestParam("deadline") @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadline, @RequestParam("description") String description, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile){
        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        ServiceModel newPost = new ServiceModel();
        newPost.setServiceTitle(title);
        newPost.setDescription(description);
        newPost.setCategoryId(getCategoryId(skillSelected));
        newPost.setDeadline(deadline);
        newPost.setPosterId(loginUser.getUserId());
        if(imageFile != null){
            try {
                // Get the byte array of the image
                byte[] imageData = imageFile.getBytes();

                // Define the directory where you want to save the image
                String uploadDirectory = AppConfig.UPLOAD_DIRECTORY;

                // Define the path where the image will be saved
                Path imagePath = Paths.get(uploadDirectory, imageFile.getOriginalFilename());

                // Check if the parent directory exists, if not, create it
                Files.createDirectories(imagePath.getParent());

                // Save the image file to the specified path
                Files.write(imagePath, imageData);

                // Return the URL of the saved image
                String imageUrl = "/img/" + imageFile.getOriginalFilename();
                newPost.setImageUrl(imageUrl);

            } catch (Exception e) {
                // Handle any exceptions, e.g., file not found, permission denied, etc.
                e.printStackTrace();
            }
        }

        postRepository.addNewPost(newPost);

        List<ServiceModel> allPosts = requestsRepository.getAllRequests();
        ServiceModel newAddedPost = allPosts.get(allPosts.size()-1);
        List<ApplicationModel> applications = applicantsRepository.getAllApplications();
        List<UserApplicationModel> relatedApp = new ArrayList<>();
        for(ApplicationModel a : applications) {
            if (a.getServiceId() == newAddedPost.getServiceId() && a.getStatus().equals("pending")){
                UserModel user = userLoginRegistrationRepository.getUserById(a.getApplicantId());
                UserApplicationModel userApplication = new UserApplicationModel(a.getApplicationId(), a.getApplicantId(), a.getServiceId(), a.getPosterId(),a.getStatus(), user.getImageUrl(), user.getFirstName(), user.getLastName(), loginUser.getSkill1(),loginUser.getSkill2(),loginUser.getSkill3());
                relatedApp.add(userApplication);
            }
        }
        model.addAttribute("relatedApp", relatedApp);
        UserModel poster = userLoginRegistrationRepository.getUserById(newAddedPost.getPosterId());
        
        String[] paragraphs = description.split("\\r?\\n");
        List<String> paragraphList = Arrays.asList(paragraphs);
        model.addAttribute("paragraphList", paragraphList);
        model.addAttribute("post", newAddedPost);
        model.addAttribute("poster", poster);

        //get notifications for user
        boolean isNewNotification = false;
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        List<NotificationModel> relatedNotifications = new ArrayList<>();
        for (NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUser.getUserId() && n.isShowNotification() == true) {
                relatedNotifications.add(n);
            }
            if(n.getUserId() == loginUser.getUserId() && n.isNewNotification() == true){
                isNewNotification = true;
            }
        }
        model.addAttribute("isNewNotification", isNewNotification);
        model.addAttribute("relatedNotifications", relatedNotifications);
        return "post_poster_view";
    }

    @PostMapping("/post_edit")
    public String displayEditPost(Model model, HttpSession session, @RequestParam("postId") int postId, @RequestParam("category") int category, @RequestParam("title") String title, @RequestParam("deadline") @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadline, @RequestParam("description") String description, @RequestParam(value = "previous_image_url", required = false) String previous_image_url){

        model.addAttribute("postId", postId);
        model.addAttribute("category", category);
        model.addAttribute("title", title);
        model.addAttribute("deadline", deadline);
        model.addAttribute("description", description);
        model.addAttribute("previous_image_url", previous_image_url);

        //get notifications for user
        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        boolean isNewNotification = false;
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        List<NotificationModel> relatedNotifications = new ArrayList<>();
        for (NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUser.getUserId() && n.isShowNotification() == true) {
                relatedNotifications.add(n);
            }
            if(n.getUserId() == loginUser.getUserId() && n.isNewNotification() == true){
                isNewNotification = true;
            }
        }
        model.addAttribute("isNewNotification", isNewNotification);
        model.addAttribute("relatedNotifications", relatedNotifications);

        return "post_edit";
    }

    @PostMapping("/edited_post")
    public String editPost(Model model, HttpSession session, @RequestParam("postId") int postId, @RequestParam("skillSelected") String skillSelected, @RequestParam("title") String title, @RequestParam("deadline") @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadline, @RequestParam("description") String description, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile){

        ServiceModel post = postRepository.getPostByServiceId(postId);
        int categoryId = getCategoryId(skillSelected);

        // reject applicants if category is changed
        if(post.getCategoryId() != categoryId) {
            List<ApplicationModel> allApplications = applicantsRepository.getAllApplications();
            for(ApplicationModel a:allApplications){
                if(a.getServiceId() == postId) {

                    //create a new reject notification
                    if(a.getStatus().equals("pending")){
                        UserModel applicant = userLoginRegistrationRepository.getUserById(a.getApplicantId());
                        UserModel poster = userLoginRegistrationRepository.getUserById(post.getPosterId());
                        NotificationModel notification = new NotificationModel();
                        notification.setSenderId(post.getPosterId());
                        notification.setSenderImg(poster.getImageUrl());
                        notification.setUserId(applicant.getUserId());
                        notification.setServiceId(post.getServiceId());
                        String content = "Your application to \"" + post.getServiceTitle() + "\" was deleted due to the requirement changed.";
                        notification.setContent(content);
                        notificationRepository.addNewNotification(notification);
                    }
                    applicantsRepository.deleteApplicationsByPostId(postId);
                }
            }
        }


        // update post
        String imageUrl = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // Get the byte array of the image
                byte[] imageData = imageFile.getBytes();

                // Define the directory where you want to save the image
                String uploadDirectory = AppConfig.UPLOAD_DIRECTORY;

                // Define the path where the image will be saved
                Path imagePath = Paths.get(uploadDirectory, imageFile.getOriginalFilename());

                // Check if the parent directory exists, if not, create it
                Files.createDirectories(imagePath.getParent());

                // Save the image file to the specified path
                Files.write(imagePath, imageData);

                // Return the URL of the saved image
                imageUrl = "/img/" + imageFile.getOriginalFilename();

            } catch (Exception e) {
                // Handle any exceptions, e.g., file not found, permission denied, etc.
                e.printStackTrace();
            }

        }
        postRepository.editPost(postId, categoryId, title, deadline, description, imageUrl);


        ServiceModel updatedPost = postRepository.getPostByServiceId(postId);
        UserModel poster = userLoginRegistrationRepository.getUserById(updatedPost.getPosterId());

        model.addAttribute("post", updatedPost);
        model.addAttribute("poster", poster);

        //sent description content to a list
        String updatedDescription = post.getDescription();
        String[] paragraphs = updatedDescription.split("\\r?\\n");
        List<String> paragraphList = Arrays.asList(paragraphs);
        model.addAttribute("paragraphList", paragraphList);

        //select related applications (when the logged-in user is the poster of the post)
        List<ApplicationModel> applications = applicantsRepository.getAllApplications();
        List<UserApplicationModel> relatedApp = new ArrayList<>();
        for(ApplicationModel a : applications) {
            if (a.getServiceId() == updatedPost.getServiceId() && a.getStatus().equals("pending")){
                UserModel user = userLoginRegistrationRepository.getUserById(a.getApplicantId());
                UserApplicationModel userApplication = new UserApplicationModel(a.getApplicationId(), a.getApplicantId(), a.getServiceId(), a.getPosterId(),a.getStatus(), user.getImageUrl(), user.getFirstName(), user.getLastName(), poster.getSkill1(),poster.getSkill2(),poster.getSkill3());
                relatedApp.add(userApplication);
            }
        }
        model.addAttribute("relatedApp", relatedApp);

        //get notifications for user
        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        boolean isNewNotification = false;
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        List<NotificationModel> relatedNotifications = new ArrayList<>();
        for (NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUser.getUserId() && n.isShowNotification() == true) {
                relatedNotifications.add(n);
            }
            if(n.getUserId() == loginUser.getUserId() && n.isNewNotification() == true){
                isNewNotification = true;
            }
        }
        model.addAttribute("isNewNotification", isNewNotification);
        model.addAttribute("relatedNotifications", relatedNotifications);

        return "post_poster_view";
    }

    @GetMapping("/post_new")
    public String displayNewPost(Model model, HttpSession session){
        //get notifications for user
        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        boolean isNewNotification = false;
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        List<NotificationModel> relatedNotifications = new ArrayList<>();
        for (NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUser.getUserId() && n.isShowNotification() == true) {
                relatedNotifications.add(n);
            }
            if(n.getUserId() == loginUser.getUserId() && n.isNewNotification() == true){
                isNewNotification = true;
            }
        }
        model.addAttribute("isNewNotification", isNewNotification);
        model.addAttribute("relatedNotifications", relatedNotifications);
        return "post_new";
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

        if(score > 100) {
            score = 100;
        } else if (score < 0){
            score = 0;
        }
        return score;
    }

    public int getCategoryId(String skillSelected){

        int categoryId = 0;

        if (skillSelected.equals("General Furniture Assembly")) {
            categoryId = 1;
        } else if (skillSelected.equals("Electrical Appliances Assembly")) {
            categoryId = 2;
        } else if (skillSelected.equals("General Mounting")) {
            categoryId = 3;
        } else if (skillSelected.equals("TV Mounting")) {
            categoryId = 4;
        } else if (skillSelected.equals("Help Moving")) {
            categoryId = 5;
        } else if (skillSelected.equals("Trash & Furniture Removal")) {
            categoryId = 6;
        } else if (skillSelected.equals("Heavy Lifting & Loading")) {
            categoryId = 7;
        } else if (skillSelected.equals("Kitchen Cleaning")) {
            categoryId = 8;
        } else if (skillSelected.equals("Bathroom Cleaning")) {
            categoryId = 9;
        } else if (skillSelected.equals("Yard Work")) {
            categoryId = 10;
        } else if (skillSelected.equals("Lawn Care")) {
            categoryId = 11;
        } else if (skillSelected.equals("Snow Removal")) {
            categoryId = 12;
        } else if (skillSelected.equals("Electrical Help")) {
            categoryId = 13;
        } else if (skillSelected.equals("Plumbing Help")) {
            categoryId = 14;
        } else if (skillSelected.equals("Minor Home Repairs")) {
            categoryId = 15;
        } else if (skillSelected.equals("Light Carpentry")) {
            categoryId = 16;
        } else if (skillSelected.equals("Indoor Painting")) {
            categoryId = 17;
        } else if (skillSelected.equals("Outdoor Painting")) {
            categoryId = 18;
        };
        
        return categoryId;
    }

}

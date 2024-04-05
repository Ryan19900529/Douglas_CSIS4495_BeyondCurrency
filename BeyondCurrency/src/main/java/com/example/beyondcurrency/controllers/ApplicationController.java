package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.ApplicationModel;
import com.example.beyondcurrency.models.NotificationModel;
import com.example.beyondcurrency.models.ServiceModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {
    @Resource
    ApplicationsRepository applicationsRepository;
    @Resource
    NotificationRepository notificationRepository;
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;
    @Resource
    PostRepository postRepository;

    @PostMapping("/newApplication")
    public String createApplication(@RequestParam int applicantId, @RequestParam int serviceId, @RequestParam int posterId) {
        ApplicationModel application = new ApplicationModel();
        application.setApplicantId(applicantId);
        application.setServiceId(serviceId);
        application.setPosterId(posterId);
        applicationsRepository.addNewApplication(application);

        //create a new notification
        UserModel applicant = userLoginRegistrationRepository.getUserById(applicantId);
        ServiceModel post = postRepository.getPostByServiceId(serviceId);
        NotificationModel notification = new NotificationModel();
        notification.setUserId(posterId);
        notification.setSenderId(applicantId);
        notification.setSenderImg(applicant.getImageUrl());
        notification.setServiceId(serviceId);
        String content = applicant.getFirstName() + " applied to your job \"" + post.getServiceTitle() + "\".";
        notification.setContent(content);
        notificationRepository.addNewNotification(notification);

        return "redirect:/";
    }

    @PostMapping("/rejectApplicant")
    public String rejectApplicant(@RequestParam int applicationId, @RequestParam int postId, @RequestParam String newStatus) {
        applicationsRepository.updateApplicationStatus(applicationId, postId, newStatus);

        //create a new reject notification
        ApplicationModel updatedApplication = applicationsRepository.getApplicationByApplicationId(applicationId);
        UserModel applicant = userLoginRegistrationRepository.getUserById(updatedApplication.getApplicantId());
        UserModel poster = userLoginRegistrationRepository.getUserById(updatedApplication.getPosterId());
        ServiceModel post = postRepository.getPostByServiceId(updatedApplication.getServiceId());
        NotificationModel notification = new NotificationModel();
        notification.setUserId(applicant.getUserId());
        notification.setSenderId(post.getPosterId());
        notification.setSenderImg(poster.getImageUrl());
        notification.setServiceId(post.getServiceId());
        String content = "Your application to \"" + post.getServiceTitle() + "\" was rejected.";
        notification.setContent(content);
        notificationRepository.addNewNotification(notification);

        return "redirect:/";
    }
}

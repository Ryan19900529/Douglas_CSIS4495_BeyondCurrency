package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.*;
import com.example.beyondcurrency.repositories.NotificationRepository;
import com.example.beyondcurrency.repositories.RequestsRepository;
import com.example.beyondcurrency.repositories.SavedTalentRepository;
import com.example.beyondcurrency.repositories.UserLoginRegistrationRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ExpertController {

    @Resource
    RequestsRepository requestsRepository;
    @Resource
    NotificationRepository notificationRepository;
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;
    @Resource
    SavedTalentRepository savedTalentRepository;

    @GetMapping("/expert/{id}")
    public String displayExpert(Model model, HttpSession session, @PathVariable(name = "id") Integer id){

        UserModel user = userLoginRegistrationRepository.getUserById(id);
        List<ServiceModel> services = requestsRepository.getRequestsByUserId(id);
        List<ServiceModel> history = new ArrayList<>();
        for(ServiceModel service : services){
            if(service.getStatus().equals("closed")){
                history.add(service);
            }
        }

        //sent about content to a list
        if(user.getAbout()!= null){String about = user.getAbout();
            String[] paragraphs = about.split("\\r?\\n");
            List<String> paragraphList = Arrays.asList(paragraphs);
            model.addAttribute("paragraphList", paragraphList);
        }

        model.addAttribute("expert", user);
        model.addAttribute("history", history);

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

        // check if it's saved talent
        List<SavedTalentModel> allSavedTalents = savedTalentRepository.getAllSavedTalents();
        boolean isSaved = false;
        for(SavedTalentModel t : allSavedTalents) {
            if(t.getUserId() == loginUser.getUserId() && t.getTalentId() == user.getUserId()){
                isSaved = true;
            }
        }

        model.addAttribute("isSavedTalent", isSaved);
        return "expert";
    }
}

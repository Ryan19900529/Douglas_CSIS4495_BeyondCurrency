package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.NotificationModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.NotificationRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SavedTalentController {
    @Resource
    NotificationRepository notificationRepository;
    @GetMapping("/talent")
    public String displaySavedTalent(Model model, HttpSession session){

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
        return "saved_talent";
    }
}

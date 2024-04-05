package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.ApplicationModel;
import com.example.beyondcurrency.models.NotificationModel;
import com.example.beyondcurrency.models.ServiceModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.NotificationRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NotificationController {
    @Resource
    NotificationRepository notificationRepository;

    @PostMapping("/notificationNotShow")
    public String setNotificationToNotShow(@RequestParam int notificationId) {
        notificationRepository.updateNotificationToNotShow(notificationId);

        return "redirect:/";
    }

    @PostMapping("/setNotificationNotNew")
    public String setNotificationNotNew(@RequestParam int loginUserId) {
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        for(NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUserId) {
                notificationRepository.updateNotificationToNotNew(n.getNotificationId());
            }
        }

        return "redirect:/";
    }
}

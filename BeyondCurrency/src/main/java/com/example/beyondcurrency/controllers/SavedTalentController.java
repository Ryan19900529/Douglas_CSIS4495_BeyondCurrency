package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.NotificationModel;
import com.example.beyondcurrency.models.SavedTalentModel;
import com.example.beyondcurrency.models.UserCardModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.NotificationRepository;
import com.example.beyondcurrency.repositories.SavedTalentRepository;
import com.example.beyondcurrency.repositories.UserLoginRegistrationRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SavedTalentController {
    @Resource
    SavedTalentRepository savedTalentRepository;
    @Resource
    NotificationRepository notificationRepository;
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;
    @PostMapping("/addSavedTalent")
    public String addSavedTalent(@RequestParam("savedTalentId") int talentId, @RequestParam("userId") int userId){
        SavedTalentModel savedTalent = new SavedTalentModel();
        savedTalent.setUserId(userId);
        savedTalent.setTalentId(talentId);
        savedTalentRepository.addNewSavedTalent(savedTalent);
        return "redirect:/";
    }

    @PostMapping("/deleteSavedTalent")
    public String deleteSavedTalent(@RequestParam("savedTalentId") int talentId, @RequestParam("userId") int userId) {
        savedTalentRepository.deleteSavedTalent(talentId,userId);
        return "redirect:/";
    }

    @GetMapping("/talent")
    public String displaySavedTalent(Model model, HttpSession session){
        List<UserModel> allUsers = userLoginRegistrationRepository.getUsers();
        List<SavedTalentModel> allSavedTalents = savedTalentRepository.getAllSavedTalents();
        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        List<UserCardModel> userCards = new ArrayList<>();
        for(UserModel u : allUsers) {
            for (SavedTalentModel t : allSavedTalents) {
                if(u.getUserId() == t.getTalentId() && t.getUserId() == loginUser.getUserId()){
                    UserCardModel card = new UserCardModel(u.getUserId(),u.getFirstName(),u.getLastName(),u.getImageUrl(),u.getTrustScore(),u.getWork_done(), true);
                    userCards.add(card);
                }
            }
        }
        model.addAttribute("users", userCards);

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
        return "saved_talent";
    }
}

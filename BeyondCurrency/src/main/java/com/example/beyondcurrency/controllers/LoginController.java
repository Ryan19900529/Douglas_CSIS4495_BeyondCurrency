package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.NotificationModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.NotificationRepository;
import com.example.beyondcurrency.repositories.UserLoginRegistrationRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;
    @Resource
    NotificationRepository notificationRepository;
    @GetMapping("")
    public String displayLogin(Model model){

        model.addAttribute("userModel", new UserModel());

        return "login";
    }

    @PostMapping("/home")
    public String loginValidation(Model model, UserModel userModel, HttpSession session) {
        List<UserModel> users = userLoginRegistrationRepository.getLoginUsers();

        boolean valid = checkLogin(userModel.getEmail(), userModel.getPassword(), users);

        if(valid) {
            UserModel loginUser = getFullInfoUser(userModel.getEmail());
            boolean isNewNotification = false;

            //get notifications for user
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
            session.setAttribute("loginUser", loginUser);
            return "home";
        } else {
            model.addAttribute("info", "Email or password is incorrect.");
            model.addAttribute("userModel", new UserModel());
            return "login";
        }
    }

    public boolean checkLogin(String email, String password, List<UserModel> users) {
        for (UserModel user : users) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public UserModel getFullInfoUser (String email) {
        List<UserModel> users = userLoginRegistrationRepository.getUsers();

        for(UserModel user : users) {
            if(email.equals(user.getEmail())) {
                return user;
            }
        }

        return null;
    }

}

package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.UserLoginRegistrationRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;

    @GetMapping("")
    public String showRegisterPage(Model model)
    {
        model.addAttribute("NewRegUser", new UserModel());
        return "registration";
    }

    @PostMapping("/home")
    public String registerNewUser(Model model, UserModel newUser, HttpSession session) {
        userLoginRegistrationRepository.addOne(newUser);

        UserModel loginUser = getFullInfoUser(newUser.getEmail());
        session.setAttribute("loginUser", loginUser);
        return "home";

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

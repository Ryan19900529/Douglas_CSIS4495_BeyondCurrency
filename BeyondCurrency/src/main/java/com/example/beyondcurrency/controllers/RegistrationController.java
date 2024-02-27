package com.example.beyondcurrency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class RegistrationController {

    @GetMapping("/registration")
    public String showRegisterPage(Model model)
    {
//        model.addAttribute("NewRegUser", new UserModel());
        return "registration";
    }
}

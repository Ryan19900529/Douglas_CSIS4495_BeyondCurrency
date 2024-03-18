package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.LoginModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.*;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String displayLogin(Model model){

        model.addAttribute("loginModel", new LoginModel());

        return "login";
    }

    @PostMapping("/home")
    public String loginValidation(Model model) {

        return "home";
    }

}

package com.example.beyondcurrency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/main")
    public String showHomePage(Model model) {

        return "main";
    }

    @GetMapping("/")
    public String showLoginHomePage(Model model) {

        return "home";
    }

}

package com.example.beyondcurrency.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DashboardController {

    @GetMapping("/dashboard")
    public String displayDashboard(Model model, HttpSession session){

        return "dashboard";
    }
}

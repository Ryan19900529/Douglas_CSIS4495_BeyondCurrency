package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("")
    public String displayLogin(Model model){

//       wef
        return "dashboard";
    }
}

package com.example.beyondcurrency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestsController {

    @GetMapping("/requests")
    public String displayRequests(Model model){

        return "request";
    }


    @GetMapping("/requests/{type}")
    public String showRequestsPage(Model model, @PathVariable(name = "type") Integer type) {

        return "request";
    }
}

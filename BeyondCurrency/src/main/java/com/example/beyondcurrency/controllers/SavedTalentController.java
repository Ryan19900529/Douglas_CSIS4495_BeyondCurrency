package com.example.beyondcurrency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SavedTalentController {

    @GetMapping("/talent")
    public String displaySavedTalent(Model model){

        return "saved_talent";
    }
}

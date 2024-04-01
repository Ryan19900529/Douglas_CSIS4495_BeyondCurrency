package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.ApplicationModel;
import com.example.beyondcurrency.repositories.ApplicationsRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {
    @Resource
    ApplicationsRepository applicationsRepository;

    @PostMapping("/newApplication")
    public String createApplication(@RequestParam int applicantId, @RequestParam int serviceId, @RequestParam int posterId) {
        ApplicationModel application = new ApplicationModel();
        application.setApplicantId(applicantId);
        application.setServiceId(serviceId);
        application.setPosterId(posterId);
        applicationsRepository.addNewApplication(application);

        return "redirect:/";
    }
}

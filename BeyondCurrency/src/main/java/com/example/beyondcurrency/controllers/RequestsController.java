package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.RequestCardModel;
import com.example.beyondcurrency.models.ServiceModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.RequestsRepository;
import com.example.beyondcurrency.repositories.UserLoginRegistrationRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RequestsController {

    @Resource
    RequestsRepository requestsRepository;
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;
    @GetMapping("/requests")
    public String displayRequests(Model model){

        return "request";
    }


    @GetMapping("/requests/{category}")
    public String showRequestsPage(Model model, @PathVariable(name = "category") Integer category) {

        List<ServiceModel> services = requestsRepository.getRequestsByCategoryId(category);
        //filter service status, then create RequestCardModel
        List<RequestCardModel> requestCards = new ArrayList<>();
        for (ServiceModel service : services){
            if (service.getStatus().equals("open")){
                UserModel user = userLoginRegistrationRepository.getUserById(service.getPosterId());
                RequestCardModel card = new RequestCardModel(service.getServiceTitle(), service.getImageUrl(), user.getUserId(), user.getSkill1(), user.getSkill2(), user.getSkill3(), user.getFirstName(), user.getLastName(), user.getImageUrl());
                requestCards.add(card);
            }
        }

        List<UserModel> users = requestsRepository.getUsersByCategoryId(category);

        //create path wording
        String path = "";
        switch (category) {
            case 1:
                path = "Assembly -> General Furniture Assembly";
                break;
            case 2:
                path = "Assembly -> Electrical Appliances Assembly";
                break;
            case 3:
                path = "Mounting -> General Mounting";
                break;
            case 4:
                path = "Mounting -> TV Mounting";
                break;
            case 5:
                path = "Moving -> Help Moving";
                break;
            case 6:
                path = "Moving -> Trash & Furniture Removal";
                break;
            case 7:
                path = "Moving -> Heavy Lifting & Loading";
                break;
            case 8:
                path = "Cleaning -> Kitchen Cleaning";
                break;
            case 9:
                path = "Cleaning -> Bathroom Cleaning";
                break;
            case 10:
                path = "Outdoor Help -> Yard Work";
                break;
            case 11:
                path = "Outdoor Help -> Lawn Care";
                break;
            case 12:
                path = "Outdoor Help -> Snow Removal";
                break;
            case 13:
                path = "Home Repairs -> Electrical Help";
                break;
            case 14:
                path = "Home Repairs -> Plumbing Help";
                break;
            case 15:
                path = "Home Repairs -> Minor Home Repairs";
                break;
            case 16:
                path = "Home Repairs -> Light Carpentry";
                break;
            case 17:
                path = "Painting -> Indoor Painting";
                break;
            case 18:
                path = "Painting -> Outdoor Painting";
                break;

        }

        model.addAttribute("cards", requestCards);
        model.addAttribute("users", users);
        model.addAttribute("path", path);

        return "request";
    }
}

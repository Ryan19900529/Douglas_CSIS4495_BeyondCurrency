package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.RequestCardModel;
import com.example.beyondcurrency.models.ServiceModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Controller
@RequestMapping("")
public class DashboardController {
    @Resource
    PostRepository postRepository;
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;
    @Resource
    ApplicationsRepository applicantsRepository;
    @Resource
    RequestsRepository requestsRepository;
    @Resource
    NotificationRepository notificationRepository;
    @GetMapping("/dashboard")
    public String displayDashboard(Model model, HttpSession session){
        UserModel loginUser = (UserModel) session.getAttribute("loginUser");
        List<ServiceModel> allServices = requestsRepository.getAllRequests();
        List<ServiceModel> yourPosts = new ArrayList<>();
        List<ServiceModel> othersDemands = new ArrayList<>();
        for(ServiceModel service : allServices) {
            if (service.getPosterId() == loginUser.getUserId()){
                yourPosts.add(service);
            }else if (service.getTakerId() == loginUser.getUserId()){
                othersDemands.add(service);
            }
        }

        List<RequestCardModel> requestCards = new ArrayList<>();
        for (ServiceModel service : othersDemands){
            UserModel user = userLoginRegistrationRepository.getUserById(service.getPosterId());
            RequestCardModel card = new RequestCardModel(service.getServiceId(), service.getServiceTitle(), service.getImageUrl(), user.getUserId(), user.getSkill1(), user.getSkill2(), user.getSkill3(), user.getFirstName(), user.getLastName(), user.getImageUrl());
            requestCards.add(card);
        }
        model.addAttribute("yourPosts", yourPosts);
        model.addAttribute("requestCards", requestCards);

        return "dashboard";
    }
}

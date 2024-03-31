package com.example.beyondcurrency.controllers;

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
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    RequestsRepository requestsRepository;

    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;

    @GetMapping("/expert/{id}")
    public String displayExpert(Model model, @PathVariable(name = "id") Integer id){

        UserModel user = userLoginRegistrationRepository.getUserById(id);
        List<ServiceModel> services = requestsRepository.getRequestsByUserId(id);
        List<ServiceModel> history = new ArrayList<>();
        for(ServiceModel service : services){
            if(service.getStatus().equals("closed")){
                history.add(service);
            }
        }

        //sent about content to a list
        if(user.getAbout()!= null){String about = user.getAbout();
            String[] paragraphs = about.split("\\r?\\n");
            List<String> paragraphList = Arrays.asList(paragraphs);
            model.addAttribute("paragraphList", paragraphList);
        }

        model.addAttribute("expert", user);
        model.addAttribute("history", history);
        return "expert";
    }
}

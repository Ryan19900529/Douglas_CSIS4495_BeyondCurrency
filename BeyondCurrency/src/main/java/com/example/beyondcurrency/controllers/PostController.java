package com.example.beyondcurrency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    @GetMapping("/post")
    public String displayPost(Model model){

        return "post";
    }

    @GetMapping("/post_poster_view")
    public String displayApplicant(Model model){

        return "post_poster_view";
    }

    @GetMapping("/post_processing")
    public String displayProcessingPost1(Model model){
        return "post_processing";
    }

    @PostMapping("/post_processing")
    public String displayProcessingPost2(Model model){
        return "post_processing";
    }

    @PostMapping("/post_completed")
    public String displayCompletedPost(Model model){
        return "post_completed";
    }
}

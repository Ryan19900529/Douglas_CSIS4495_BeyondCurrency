package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.UserLoginRegistrationRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;

    @GetMapping("")
    public String showRegisterPage(Model model)
    {
        model.addAttribute("NewRegUser", new UserModel());
        return "registration";
    }

    @PostMapping("/home")
    public String registerNewUser(Model model, UserModel newUser, HttpSession session, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if(imageFile != null){
            try {
                // Get the byte array of the image
                byte[] imageData = imageFile.getBytes();

                // Define the directory where you want to save the image
                String uploadDirectory = "/Users/Ryan/Desktop/Douglas/Winter 2024/Applied Research Project/Project_RYa196/Implementation/Douglas_CSIS4495_BeyondCurrency/BeyondCurrency/src/main/resources/static/img";

                // Define the path where the image will be saved
                Path imagePath = Paths.get(uploadDirectory, imageFile.getOriginalFilename());

                // Save the image file to the specified path
                Files.write(imagePath, imageData);

                // Return the URL of the saved image
                String imageUrl = "/img/" + imageFile.getOriginalFilename();
                newUser.setImageUrl(imageUrl);
                // Now you can use the imageUrl as needed
                // For example, you can save it to the database or use it in your application

            } catch (Exception e) {
                // Handle any exceptions, e.g., file not found, permission denied, etc.
                e.printStackTrace();
            }
        }

        userLoginRegistrationRepository.addOne(newUser);

        UserModel loginUser = getFullInfoUser(newUser.getEmail());
        session.setAttribute("loginUser", loginUser);
        return "home";

    }

    public UserModel getFullInfoUser (String email) {
        List<UserModel> users = userLoginRegistrationRepository.getUsers();

        for(UserModel user : users) {
            if(email.equals(user.getEmail())) {
                return user;
            }
        }

        return null;
    }
}

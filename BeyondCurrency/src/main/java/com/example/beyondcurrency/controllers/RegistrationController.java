package com.example.beyondcurrency.controllers;

import com.example.beyondcurrency.AppConfig;
import com.example.beyondcurrency.models.NotificationModel;
import com.example.beyondcurrency.models.UserModel;
import com.example.beyondcurrency.repositories.NotificationRepository;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Resource
    UserLoginRegistrationRepository userLoginRegistrationRepository;
    @Resource
    NotificationRepository notificationRepository;

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
                String uploadDirectory = AppConfig.UPLOAD_DIRECTORY;

                // Define the path where the image will be saved
                Path imagePath = Paths.get(uploadDirectory, imageFile.getOriginalFilename());

                // Check if the parent directory exists, if not, create it
                Files.createDirectories(imagePath.getParent());

                // Save the image file to the specified path
                Files.write(imagePath, imageData);

                // Return the URL of the saved image
                String imageUrl = "/img/" + imageFile.getOriginalFilename();
                newUser.setImageUrl(imageUrl);

            } catch (Exception e) {
                // Handle any exceptions, e.g., file not found, permission denied, etc.
                e.printStackTrace();
            }
        }

        userLoginRegistrationRepository.addOne(newUser);

        UserModel loginUser = getFullInfoUser(newUser.getEmail());
        session.setAttribute("loginUser", loginUser);

        //get notifications for user
        boolean isNewNotification = false;
        List<NotificationModel> allNotifications = notificationRepository.getAllNotifications();
        List<NotificationModel> relatedNotifications = new ArrayList<>();
        for (NotificationModel n : allNotifications) {
            if(n.getUserId() == loginUser.getUserId() && n.isShowNotification() == true) {
                relatedNotifications.add(n);
            }
            if(n.getUserId() == loginUser.getUserId() && n.isNewNotification() == true){
                isNewNotification = true;
            }
        }
        model.addAttribute("isNewNotification", isNewNotification);
        model.addAttribute("relatedNotifications", relatedNotifications);

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

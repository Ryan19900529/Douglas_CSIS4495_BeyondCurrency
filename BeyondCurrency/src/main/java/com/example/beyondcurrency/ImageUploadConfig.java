package com.example.beyondcurrency;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageUploadConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/img/**")
                .addResourceLocations("file:\\Users\\Ryan\\Desktop\\Douglas\\Winter 2024\\Applied Research Project\\Project_RYa196\\Implementation\\Douglas_CSIS4495_BeyondCurrency\\BeyondCurrency\\src\\main\\resources\\static\\img\\");
    }
}

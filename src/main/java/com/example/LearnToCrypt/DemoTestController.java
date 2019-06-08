package com.example.LearnToCrypt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class DemoTestController implements WebMvcConfigurer {
	
	@RequestMapping("/")
    public String sayHello() {
        return "Milestone Heroku and Jenkkins";
        // Dummy commit for Milestone-1 
	}
}
package com.example.LearnToCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class DemoController implements WebMvcConfigurer {
	
	@RequestMapping("/")
    public String sayHello() {
        return "Milestone 1 done. 2nd testing";
        // Dummy commit for Milestone-1 
	}
}
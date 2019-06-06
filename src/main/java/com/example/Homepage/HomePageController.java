package com.example.Homepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class HomePageController implements WebMvcConfigurer {
	
	@GetMapping("/homepage")
    public String displayHomepage() {
        return "homepage.html";
    }
	
	@GetMapping("/")
    public String displayDefaultHomepage() {
        return "homepage.html";
    }
}
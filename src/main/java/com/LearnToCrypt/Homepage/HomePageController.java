package com.LearnToCrypt.Homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class HomePageController implements WebMvcConfigurer {
	
	@GetMapping("/homepage")
    public String displayHomepage() {
        return "homepage.html";
    }
	
	@GetMapping("/")
    public String displayHomepageDefault() {
        return "redirect:/homepage";
    }
}
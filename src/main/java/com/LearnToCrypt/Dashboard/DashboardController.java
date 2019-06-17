package com.LearnToCrypt.Dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class DashboardController implements WebMvcConfigurer {
	
	@GetMapping("/dashboard")
    public String displayDashboard(ModelMap model, @ModelAttribute("username") final Object username) {
		model.put("username", username);
        return "dashboard";
    }
}
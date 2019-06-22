package com.LearnToCrypt.Dashboard;


import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class DashboardController implements WebMvcConfigurer {
    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	
	AuthenticationManager authenticationManager;
	
	public DashboardController() {
		authenticationManager = AuthenticationManager.instance();
	}
	
	@GetMapping("/dashboard")
    public String displayDashboard(HttpSession httpSession, ModelMap model) {
		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
		if(!isUserAuthenticated) {
			return "redirect:/login";
		}
		
		String username = authenticationManager.getUsername(httpSession);
		model.put("username", username);

        logger.info("user \""+username+"\" accessed dashboard!");

        return "dashboard";
    }
}
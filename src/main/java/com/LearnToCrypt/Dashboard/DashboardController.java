package com.LearnToCrypt.Dashboard;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class DashboardController implements WebMvcConfigurer {
	
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
		
        return "dashboard";
    }
}
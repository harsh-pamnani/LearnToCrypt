package com.LearnToCrypt.Dashboard;


import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.SignIn.AuthenticationManager;

import java.util.List;

@Controller
public class DashboardController implements WebMvcConfigurer {
    private static final Logger logger = LogManager.getLogger(DashboardController.class);
    public static final String INSTRUCTOR_ROLE = "Instructor";

    AuthenticationManager authenticationManager;
    DashboardAlgorithms dashboardAlgorithms;

    public DashboardController() {
		authenticationManager = AuthenticationManager.instance();
        dashboardAlgorithms = new DashboardAlgorithms();
	}
	
	@GetMapping("/dashboard")
    public String displayDashboard(HttpSession httpSession, ModelMap model) {
		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
		if(!isUserAuthenticated) {
			return "redirect:/login";
		} else {
			String email = authenticationManager.getEmail(httpSession);
			String role =authenticationManager.getUserRole(httpSession);
			String username = authenticationManager.getUsername(httpSession);
			model.put("username", username);

			if(role.equals(INSTRUCTOR_ROLE)) {
				return "instructorDashboard";
			}

			dashboardAlgorithms.addAlgorithmsToDashboard(email, model);
			logger.info("user \"" + username + "\" accessed dashboard!");
	        return "dashboard";
		}
    }
}
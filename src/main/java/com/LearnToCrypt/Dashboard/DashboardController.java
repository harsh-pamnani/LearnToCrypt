package com.LearnToCrypt.Dashboard;


import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class DashboardController implements WebMvcConfigurer {
    private static final Logger logger = LogManager.getLogger(DashboardController.class);
	
	AuthenticationManager authenticationManager;
	DAOAbstractFactory daoAbstractFactory;
	
	public DashboardController() {
		authenticationManager = AuthenticationManager.instance();
		daoAbstractFactory = new DAOAbstractFactory();
	}
	
	@GetMapping("/dashboard")
    public String displayDashboard(HttpSession httpSession, ModelMap model) {
		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
		if(!isUserAuthenticated) {
			return "redirect:/login";
		} else {
			String email = authenticationManager.getEmail(httpSession);
			String role = daoAbstractFactory.createUserDAO().getUserRole(email);
			
			String username = authenticationManager.getUsername(httpSession);
			model.put("username", username);

			if(role.equals("Instructor")) {
				return "instructorDashboard";
			}
			
			IAlgorithmDAO algorithmDAO = daoAbstractFactory.createAlgorithmDAO();
			IUserDAO userDAO = daoAbstractFactory.createUserDAO();
			String className = userDAO.getUserClass(email);

			model.addAttribute("basic",algorithmDAO.getAlgorithmByLevelAndClass(1,className));
			model.addAttribute("intermediate",algorithmDAO.getAlgorithmByLevelAndClass(2,className));
			logger.info("user \"" + username + "\" accessed dashboard!");
	        return "dashboard";
		}
    }
}
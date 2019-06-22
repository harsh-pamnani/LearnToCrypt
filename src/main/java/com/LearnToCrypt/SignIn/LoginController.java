package com.LearnToCrypt.SignIn;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.IBusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;

@Controller
public class LoginController implements WebMvcConfigurer {

	ValidateUserCredentials validateUserCredentials;
	IBusinessModelAbstractFactory businessModelAbstractFactory;

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	AuthenticationManager authenticationManager;

	
    public LoginController() {
    	validateUserCredentials = new ValidateUserCredentials();
    	businessModelAbstractFactory = new BusinessModelAbstractFactory();
    	authenticationManager = AuthenticationManager.instance();
    }
    
	@GetMapping("/login")
    public String displayLogin(HttpSession httpSession, ModelMap model) {
		
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("user", businessModelAbstractFactory.createUser());		
		return "login.html";
    }
	
	@PostMapping("/login")
    public String showDashboard(HttpSession httpSession, ModelMap model, User user) {	
		boolean isUserValid = validateUserCredentials.validateCredentials(user);
  
		if (!isUserValid) {
			logger.error("User \""+user.getEmail()+"\" entered invalid credentials");
            model.put("invalidLogin", "Invalid Credentials");
            return "login";
        }
		
		logger.error("User \""+authenticationManager.getUsername(httpSession)+"\" login success");
					
		authenticationManager.addAuthenticatedUser(httpSession, user.getEmail());
    return "redirect:/dashboard";
    }
}
package com.LearnToCrypt.SignIn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.LearnToCrypt.BusinessModels.User;

@Controller
public class LoginController implements WebMvcConfigurer {
	
	ValidateUserCredentials validateUserCredentials;

    public LoginController() {
    	validateUserCredentials = new ValidateUserCredentials();
    }
    
	@GetMapping("/login")
    public String displayLogin(ModelMap model) {
		
		model.addAttribute("user", new User());
		
		return "login.html";
    }
	
	@PostMapping("/login")
    public String showDashboard(ModelMap model, User user, RedirectAttributes redirectAttributes) {
		
		// Credential validations will go here.
		boolean isUserValid = validateUserCredentials.validateCredentials(user);
        
		if (!isUserValid) {
            model.put("invalidLogin", "Invalid Credentials");
            return "login";
        }
		
		// This logic will be updated to get the user's name from database.
		redirectAttributes.addFlashAttribute("name", "Harsh Pamnani");
		
        return "redirect:/dashboard";
    }
}
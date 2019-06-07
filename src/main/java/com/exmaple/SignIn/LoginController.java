package com.exmaple.SignIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.services.LoginValidatorService;

@Controller
public class LoginController implements WebMvcConfigurer {
	
	@Autowired
    LoginValidatorService loginService;
	
	@GetMapping("/login")
    public String displayLogin() {
        return "login.html";
    }
	
	@PostMapping("/login")
    public String showDashboard(ModelMap model, @RequestParam String email, @RequestParam String password) {
		
		boolean isUserValid = loginService.validateLoginCredentials(email, password);
        
		if (!isUserValid) {
            model.put("invalidLogin", "Invalid Credentials");
            return "login";
        }
		// This logic will be updated to get the user's name from database.
        model.put("username", "Harsh Pamnani");
        model.put("email", email);
        model.put("password", password);
        
        return "redirect:/dashboard";
    }
}
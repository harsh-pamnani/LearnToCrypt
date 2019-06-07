package com.example.SignUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.services.LoginValidatorService;
import com.example.services.SignUpValidatorService;

@Controller
public class SignUpController implements WebMvcConfigurer {
	
	@Autowired
    SignUpValidatorService singUpService;
	
	@GetMapping("/signup")
    public String displayLogin() {
        return "registration.html";
    }
	
	@PostMapping("/signup")
    public String showDashboard(ModelMap model, @RequestParam String username, @RequestParam String email, 
    		@RequestParam String password, @RequestParam String confirmPassword) {
		
		String isSignUpValidError = singUpService.validateSignUpForm(username, email, password, confirmPassword);
        
		if (!isSignUpValidError.equals("")) {
			model.put("username", username);
			model.put("email", email);
            model.put("invalidSignup", isSignUpValidError + ". Registration Failed.");
            return "registration";
        }
		// This logic will be updated to get the user's name from database.
        model.put("username", "Harsh Pamnani");
		model.put("email", email);
        model.put("password", password);
        
        return "redirect:/dashboard";
    }
}
package com.LearnToCrypt.signin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.LearnToCrypt.services.LoginValidatorService;

@Controller
public class LoginController implements WebMvcConfigurer {
	
	@Autowired
    LoginValidatorService loginService;
	
	@GetMapping("/login")
    public String displayLogin(ModelMap model) {
		
		String email = "";
		String password = "";
		
		model.addAttribute("email",email);		
		model.addAttribute("password",password);
		
		return "login.html";
    }
	
	@PostMapping("/login")
    public String showDashboard(ModelMap model, @RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {
		
		boolean isUserValid = loginService.validateLoginCredentials(email, password);
        
		if (!isUserValid) {
            model.put("invalidLogin", "Invalid Credentials");
            return "login";
        }
		
		// This logic will be updated to get the user's name from database.
		redirectAttributes.addFlashAttribute("username", "Harsh Pamnani");
		model.put("email", email);
        model.put("password", password);
        
        return "redirect:/dashboard";
    }
}
package com.LearnToCrypt.SignIn;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	AuthenticationManager authenticationManager;
	
	public LogoutController() {
		authenticationManager = AuthenticationManager.instance();
	}

	@GetMapping("/logout")
    public String logoutUser(HttpSession httpSession) {
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			authenticationManager.removeAuthenticatedUser(httpSession);			
		}
		return "redirect:/homepage";
    }
}
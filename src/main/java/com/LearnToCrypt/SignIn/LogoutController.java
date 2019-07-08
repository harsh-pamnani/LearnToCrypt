package com.LearnToCrypt.SignIn;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.LearnToCrypt.app.LearnToCryptApplication;

@Controller
public class LogoutController {

	AuthenticationManager authenticationManager;
    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	public LogoutController() {
		authenticationManager = AuthenticationManager.instance();
	}

	@GetMapping("/logout")
    public String logoutUser(HttpSession httpSession) {
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			logger.info(authenticationManager.getEmail(httpSession) + " user logged out.");
			
			authenticationManager.removeAuthenticatedUser(httpSession);
		}
		return "redirect:/homepage";
    }
}
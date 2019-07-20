package com.LearnToCrypt.Homepage;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.HashingAlgorithm.MD5;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;

@Controller
public class HomePageController implements WebMvcConfigurer {
	
	AuthenticationManager authenticationManager;
	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	
	public HomePageController() {
		authenticationManager = AuthenticationManager.instance();
	}
	
	@GetMapping("/homepage")
    public String displayHomepage(HttpSession httpSession, ModelMap model) {
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			String username = authenticationManager.getUsername(httpSession);
			model.put("username", username);
			
			logger.info(username + " accessed homepage");
			
			return "homepageLoggedInUser.html";
		}
		
		logger.info("Homepage accessed");
        return "homepage.html";
    }
	
	@GetMapping("/")
    public String displayHomepageDefault() {
		logger.info("/ mapping redirecting to /homepage");
        return "redirect:/homepage";
    }
	
	@GetMapping("/learn")
    public String startLearning(HttpSession httpSession) {
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			String username = authenticationManager.getUsername(httpSession);
			
			logger.info(username + "clicked on Start Learning button");
			return "redirect:/dashboard";
		}
		
		logger.info("/learn redirecting to /login because user not logged in.");
        return "redirect:/login";
    }
}
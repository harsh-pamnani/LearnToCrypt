package com.LearnToCrypt.Homepage;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class HomePageController implements WebMvcConfigurer {
	
	AuthenticationManager authenticationManager;
	
	public HomePageController() {
		authenticationManager = AuthenticationManager.instance();
	}
	
	@GetMapping("/homepage")
    public String displayHomepage(HttpSession httpSession, ModelMap model) {
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			String username = authenticationManager.getUsername(httpSession);
			model.put("username", username);
			
			return "homepageLoggedInUser.html";
		}
        return "homepage.html";
    }
	
	@GetMapping("/")
    public String displayHomepageDefault() {
        return "redirect:/homepage";
    }
	
	@GetMapping("/learn")
    public String startLearning(HttpSession httpSession) {
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			return "redirect:/dashboard";
		}
        return "redirect:/login";
    }
}
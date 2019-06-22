package com.LearnToCrypt.Dashboard;

import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class DashboardController implements WebMvcConfigurer {
    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	
	@GetMapping("/dashboard")
    public String displayDashboard(ModelMap model, @ModelAttribute("username") final Object username) {
		model.put("username", username);
        logger.info("access dashboard!");
        return "dashboard";
    }
}
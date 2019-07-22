package com.LearnToCrypt.Playground;

import com.LearnToCrypt.SignIn.AuthenticationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ComparisonController implements WebMvcConfigurer {
	private static final Logger logger = LogManager.getLogger(ComparisonController.class);
	private AuthenticationManager authenticationManager;
	private IComparisonResultSet comparisonResultSet;
	private IComparisonResult comparisonResult;
	private ArrayList<IComparisonResult> results;

	public ComparisonController() {
		authenticationManager = AuthenticationManager.instance();

	}

	@GetMapping("/comparison")
	public String displayComparison(HttpSession httpSession,
									ModelMap model) {
		results = new ArrayList<>();
		comparisonResultSet = (ComparisonResultSet) httpSession.getAttribute("resultSet");
		httpSession.removeAttribute("resultSet");
		logger.info("Loading Comparison Result");
		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
		if(!isUserAuthenticated) {
			logger.error("User is not logged in. Redirecting to Login page");
			return "redirect:/login";
		} else {
			String username = authenticationManager.getUsername(httpSession);
			model.put("username", username);
		}
		while (comparisonResultSet.hasNextResult()) {
			comparisonResult = comparisonResultSet.getNextResult();
			results.add(comparisonResult);
		}
		model.put("algorithmResults", results);
		logger.info("Comparison results loaded");
		return "comparison";
	}
}

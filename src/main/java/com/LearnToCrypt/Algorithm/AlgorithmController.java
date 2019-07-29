package com.LearnToCrypt.Algorithm;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.AlgorithmContext;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.AlgorithmAbstractFactory;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;
import com.LearnToCrypt.BusinessModels.Algorithm;
import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class AlgorithmController implements WebMvcConfigurer {

	private static final Logger logger = LogManager.getLogger(AlgorithmController.class);

	private AuthenticationManager authenticationManager;
	private ManageAlgorithm manageAlgorithm;

	private String username;
	private String useremail;
	private String algorithmName;
	private String algorithmDescription;
	private String algorithmImage;

	public AlgorithmController() {
		authenticationManager = AuthenticationManager.instance();
		manageAlgorithm = new ManageAlgorithm();
	}

	@GetMapping("/algorithm")
	public String getAlgorithmPage(HttpSession httpSession,
			@RequestParam(name = "alg", required = false, defaultValue = "Algorithm") String alg, Model model) {

		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
		if (!isUserAuthenticated) {
			return "redirect:/login";
		}
		username = authenticationManager.getUsername(httpSession);

		Algorithm algorithm = manageAlgorithm.getAlgorithm(alg);
		logger.info("user \"" + username + "\" accessed " + algorithm.getName());
		if (algorithm.getName() == null) {
			setModelAttributes(model);
			return "dashboard";
		}

		algorithmName = algorithm.getName();
		algorithmDescription = algorithm.getDescription();
		algorithmImage = algorithm.getImage();
		setModelAttributes(model);

		return "algorithm";
	}

	@PostMapping("/algorithm")
	public String submit(HttpSession httpSession, @ModelAttribute UserInput userInput, Model model) {

		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
		if (!isUserAuthenticated) {
			return "redirect:/login";
		}
		username = authenticationManager.getUsername(httpSession);
		useremail = authenticationManager.getEmail(httpSession);
		setModelAttributes(model);

		AlgorithmAbstractFactory algorithmFactory = new AlgorithmAbstractFactory();
		IEncryptionAlgorithmStrategy cipherStrategy;

		String pageToDisplay;
		try {
			cipherStrategy = algorithmFactory.createAlgorithm(algorithmName);

			AlgorithmContext algorithmContext = new AlgorithmContext(cipherStrategy);
			algorithmContext.executeStrategy(userInput, useremail, model);

			logger.info("user \"" + username + "\" tested " + algorithmName);
			pageToDisplay =  "algorithm";
		} catch (NoSuchAlgorithmException e) {
			pageToDisplay = "redirect:/dashboard";
			logger.error("Unknown algorithm request : " + algorithmName);
		}

		return pageToDisplay;
	}

	private void setModelAttributes(Model model) {
		model.addAttribute("username", username);
		model.addAttribute("userInput", new UserInput());

		model.addAttribute("alg", algorithmName);
		model.addAttribute("url", "images/" + algorithmImage);
		model.addAttribute("description", algorithmDescription);
	}
}

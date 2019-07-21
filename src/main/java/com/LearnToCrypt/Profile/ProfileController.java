package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.INameSetterDAO;
import com.LearnToCrypt.DAO.IPasswordUpdaterDAO;
import com.LearnToCrypt.DAO.ProfileUpdateDAO;
import com.LearnToCrypt.HashingAlgorithm.IHash;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController implements WebMvcConfigurer {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	private IUserProfileBridge profile;
	private IPasswordChanger passwordChanger;
	private IUserNameChanger userNameChanger;
	private String username;
	private String email;
	private AuthenticationManager authenticationManager;
	private IProfileValidator profileValidator;
	private INameSetterDAO nameSetter;
	private IPasswordUpdaterDAO passwordUpdater;
	private ProfileUpdater profileUpdater;
	private IHash hash;

	ProfileController() {
		nameSetter = new ProfileUpdateDAO();
		passwordUpdater = new ProfileUpdateDAO();
		profileUpdater = new ProfileUpdater(nameSetter, passwordUpdater);
		passwordChanger = profileUpdater;
		userNameChanger = profileUpdater;
		authenticationManager = AuthenticationManager.instance();
		hash = new MD5();
	}

	@GetMapping("/profile")
	public String displayProfile(ModelMap model,
								 HttpSession httpSession,
								 @RequestParam (required = false) String errorText,
								 @RequestParam (required = false) String successText ) {
		logger.info("Loading profile page");
		if (authenticationManager.isUserAuthenticated(httpSession)) {
			email = authenticationManager.getEmail(httpSession);
			profile = new UserProfile(email);
			model.put("username", authenticationManager.getUsername(httpSession));
			model.put("email", profile.getEmail());
			model.put("role", profile.getRole());
			if (null != errorText) {
				logger.error("Validation Error: " + errorText);
				model.put("errorText", errorText);
			}
			else if (null != successText) {
				logger.info("Request successful: " + successText);
				model.put("successText", successText);
			}
			return ("profile");
		}
		else {
			return ("redirect:/login");
		}
	}

	@PostMapping("/profile")
	public String updateProfile(ModelMap model,
								HttpSession httpSession,
								@RequestParam String username,
								@RequestParam String newPass,
								@RequestParam String confirmPass) {
		logger.info("Processing profile update request");
		email = authenticationManager.getEmail(httpSession);
		profile = new UserProfile(email);
		profileValidator = new ProfileValidator(profile, hash);

		if(null != username && !username.equals(profile.getUserName())) {
			logger.info("Processing Request to update username to " + username);
			String error = profileValidator.isNameValid(username);
			if (null == error) {
				userNameChanger.changeName(email, username);
			}
			else {
				logger.error("Error processing request: " + error);
				return ("redirect:/profile?errorText=" + error);
			}
		}

		if(null != newPass && !newPass.equals("")) {
			logger.info("Processing request to update password");
			String error = profileValidator.isPasswordValid(newPass, confirmPass);
			if (null == error) {
				email = authenticationManager.getEmail(httpSession);
				passwordChanger.changePassword(email, newPass);
			}
			else {
				logger.error("Error processing request: " + error);
				return ("redirect:/profile?errorText=" + error);
			}
		}
		return ("redirect:/profile?successText=Profile Updated Successfully");
	}
}

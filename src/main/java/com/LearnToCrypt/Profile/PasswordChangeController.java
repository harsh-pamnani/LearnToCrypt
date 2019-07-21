package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.*;
import com.LearnToCrypt.HashingAlgorithm.IHash;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class PasswordChangeController implements WebMvcConfigurer {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	private String resetToken;
	private IProfileValidator profileValidator;
	private IUserProfileBridge profile;
	private IPasswordUpdaterDAO passwordUpdaterDAO;
	private IDAOAbstractFactory abstractFactory;
	private IPasswordChanger passwordChanger;
	private IHash hash;
	private IPasswordUpdaterDAO passwordUpdater;
	private INameSetterDAO nameSetter;
	public PasswordChangeController() {
		abstractFactory = new DAOAbstractFactory();
		passwordUpdaterDAO = abstractFactory.createPasswordSetterDAO();
		hash = new MD5();
		nameSetter = new ProfileUpdateDAO();
		passwordUpdater = new ProfileUpdateDAO();
		passwordChanger = new ProfileUpdater(nameSetter, passwordUpdater);
		profileValidator = new ProfileValidator(profile, hash);
	}

	@GetMapping("/passwordchange")
	public String displayPassChange(ModelMap model,
									@RequestParam String token) {
		resetToken = token;
		return ("passwordchange");
	}

	@GetMapping("/changesuccess")
	public String displaySuccessChange(ModelMap model,
									   @RequestParam (required = false) String isPasswordChanged) {
		logger.info("Loading password change success page");
		if(null == isPasswordChanged) {
			logger.error("Password not changed. Redirecting to homepage.");
			return("redirect:/homepage");
		}
		return ("changesuccess");
	}

	@PostMapping("/passwordchange")
	public String resetPassword(ModelMap model,
								@RequestParam String newPass,
								@RequestParam String confirmPass) {
		logger.info("Processing password reset request");
		if(null == newPass || null == confirmPass) {
			logger.error("Error processing request: Password Empty");
			model.put("errorText", "Password cannot be empty");
			return ("passwordchange");
		}
		else {
			String email = passwordUpdaterDAO.getEmailFromToken(resetToken);
			profile = new UserProfile(email);
			String error = profileValidator.isPasswordValid(newPass, confirmPass);
			if (null == error) {
				passwordChanger.changePassword(email, newPass);
				logger.info("Password changed successfully");
				return ("redirect:/changesuccess?isPasswordChanged=yes");
			}
			else {
				logger.error("Error processing request: " + error);
				model.put("errorText", error);
				return ("passwordchange");
			}
		}
	}
}

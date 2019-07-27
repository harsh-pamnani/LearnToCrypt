package com.LearnToCrypt.Profile;

import com.LearnToCrypt.HashingAlgorithm.IHash;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IPasswordUpdaterDAO;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

@Controller
public class PasswordChangeController implements WebMvcConfigurer {

	private static final Logger logger = LogManager.getLogger(PasswordChangeController.class);
	private String resetToken;
	private IProfileValidator profileValidator;
	private IUserProfileBridge profile;
	private IDAOAbstractFactory abstractFactory;
	private IPasswordChanger passwordChanger;
	private IUserNameChanger nameChanger;
	private IHash hash;

	public PasswordChangeController() {
		abstractFactory = new DAOAbstractFactory();
		passwordChanger = new PasswordChanger(abstractFactory);
		nameChanger = new UserNameChanger(abstractFactory);
		hash = new MD5();
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
			try {
				String email = passwordChanger.getEmailFromToken(resetToken);
				profile = new UserProfile(email, abstractFactory);
				profileValidator = new ProfileValidator(profile, hash);
				IUpdateProfile updater = new ProfileUpdater(profileValidator, nameChanger, passwordChanger);
				updater.update(profile, null, newPass, confirmPass);
				logger.info("Password Changed Successfully");
				return ("redirect:/changesuccess?isPasswordChanged=yes");

			} catch (SQLException e) {

				logger.error("Error Connecting to database: " + e.getMessage());
				logger.error("SQL State: " + e.getSQLState());
				logger.error(e.getStackTrace());
				logger.info("Redirecting with error message: " + e.getMessage());
				model.put("errorText", e.getMessage());
				return ("passwordchange");

			} catch (ValidationException e) {
				logger.error("Validation Error: " + e.getMessage());
				logger.error(e.getStackTrace());
				logger.info("Redirecting with error message: " + e.getMessage());
				model.put("errorText", e.getMessage());
				return ("passwordchange");
			}
		}
	}
}

package com.LearnToCrypt.Profile;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
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

import com.LearnToCrypt.SignIn.AuthenticationManager;

import java.sql.SQLException;

@Controller
public class ProfileController implements WebMvcConfigurer {

	private static final Logger logger = LogManager.getLogger(ProfileController.class);
	private IUserProfileBridge profile;
	private IPasswordChanger passwordChanger;
	private IUserNameChanger userNameChanger;
	private String email;
	private AuthenticationManager authenticationManager;
	private IProfileValidator profileValidator;
	private IProfileCreator profileCreator;
	private IDAOAbstractFactory abstractFactory;
	private IHash hash;
	private IUpdateProfile updater;

	ProfileController() {
		authenticationManager = AuthenticationManager.instance();
		profileCreator = ProfileCreator.getInstance();
		abstractFactory = new DAOAbstractFactory();
		passwordChanger = new PasswordChanger(abstractFactory);
		userNameChanger = new UserNameChanger(abstractFactory);
		hash = new MD5();
	}

	@GetMapping("/profile")
	public String displayProfile(ModelMap model,
								 HttpSession httpSession,
								 @RequestParam (required = false) String errorText,
								 @RequestParam (required = false) String successText ) {
		logger.info("Loading profile page");
		try {
			profile = profileCreator.getProfile(authenticationManager, httpSession, abstractFactory);
			model.put("username", profile.getUserName());
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

		} catch (AuthenticationException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
			logger.info("Redirecting to login page");
			return ("redirect:/login");

		} catch (SQLException e) {
			logger.error("Error Connecting to database: " + e.getMessage());
			logger.error("SQL State: " + e.getSQLState());
			logger.error(e.getStackTrace());
			return ("profile");
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
		try {

			profile = profileCreator.getProfile(authenticationManager, httpSession, abstractFactory);
			profileValidator = new ProfileValidator(profile, hash);
			updater = new ProfileUpdater(profileValidator, userNameChanger, passwordChanger);
			updater.update(profile, username, newPass, confirmPass);
			return ("redirect:/profile?successText=Profile Updated Successfully");

		} catch (SQLException e) {
			logger.error("Error Connecting to database: " + e.getMessage());
			logger.error("SQL State: " + e.getSQLState());
			logger.error(e.getStackTrace());
			logger.info("Redirecting with error message: " + e.getMessage());
			return ("redirect:/profile?errorText=" + e.getMessage());

		} catch (AuthenticationException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
			logger.info("Redirecting to login page");
			return ("redirect:/login");

		} catch (ValidationException e) {
			logger.error("Validation Error: " + e.getMessage());
			logger.error(e.getStackTrace());
			logger.info("Redirecting with error message: " + e.getMessage());
			return ("redirect:/profile?errorText=" + e.getMessage());
		}
	}
}

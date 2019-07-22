package com.LearnToCrypt.Profile;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import com.LearnToCrypt.Validations.IValidation;
import com.LearnToCrypt.Validations.UserProfileNameUpdateValidation;
import com.LearnToCrypt.Validations.UserProfilePasswordUpdateValidation;

public class ProfileValidator implements IProfileValidator {

	private static final Logger logger = LogManager.getLogger(ProfileValidator.class);
	private UserProfileNameUpdateValidation nameValidationRules = null;
	private UserProfilePasswordUpdateValidation passwordValidationRules = null;
	private User user;
	private MD5 md5;

	public ProfileValidator(IUserProfileBridge profile) {
		nameValidationRules = new UserProfileNameUpdateValidation();
		passwordValidationRules = new UserProfilePasswordUpdateValidation();
		user = profile.getUser();
		md5 = new MD5();
	}

	@Override
	public String isNameValid(String name) {
		logger.info("Validating name: " + name);
		List<IValidation> rules = nameValidationRules.getValidationRules();
		String formError = null;
		user.setName(name);
		for (IValidation rule: rules) {
			if(!rule.isValid(user, "")) {
				formError = rule.getError();
				break;
			}
		}
		logger.info("Validation Done. " + formError);
		return formError;
	}

	@Override
	public String isPasswordValid(String password, String confirmPassword) {
		logger.info("Validating Password");
		List<IValidation> rules = passwordValidationRules.getValidationRules();
		String formError = null;
		String hashedPassword = md5.generateMD5HashValue(password);
		if (hashedPassword.equals(user.getPassword())) {
			formError = "New password cannot be same as the old password";
			logger.error("Validation Error: " + formError);
			return formError;
		}
		user.setPassword(password);
		for (IValidation rule: rules) {
			if(!rule.isValid(user, confirmPassword)) {
				formError = rule.getError();
				break;
			}
		}
		logger.info("Validation Done. " + formError);
		return formError;
	}
}

package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.IUser;
import com.LearnToCrypt.HashingAlgorithm.IHash;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import com.LearnToCrypt.Validations.IValidation;
import com.LearnToCrypt.Validations.UserProfileNameUpdateValidation;
import com.LearnToCrypt.Validations.UserProfilePasswordUpdateValidation;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProfileValidator implements IProfileValidator {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	private UserProfileNameUpdateValidation nameValidationRules = null;
	private UserProfilePasswordUpdateValidation passwordValidationRules = null;
	private IUser user;
	private IHash hash;

	public ProfileValidator(IUserProfileBridge profile, IHash hash) {
		nameValidationRules = new UserProfileNameUpdateValidation();
		passwordValidationRules = new UserProfilePasswordUpdateValidation();
		user = profile.getUser();
		this.hash = hash;
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
		String hashedPassword = hash.generateHashValue(password);
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

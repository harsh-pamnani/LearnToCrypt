package com.LearnToCrypt.Profile;

import java.util.List;

import com.LearnToCrypt.HashingAlgorithm.IHash;
import com.LearnToCrypt.Validations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.ValidationException;

public class ProfileValidator implements IProfileValidator {

	private static final Logger logger = LogManager.getLogger(ProfileValidator.class);
	private UserProfileNameUpdateValidation nameValidationRules;
	private UserProfilePasswordUpdateValidation passwordValidationRules;
	private IHash hash;
	private IUserProfileBridge profile;
	private IValidationParams params;

	public ProfileValidator(IUserProfileBridge profile, IHash hash) {
		nameValidationRules = new UserProfileNameUpdateValidation();
		passwordValidationRules = new UserProfilePasswordUpdateValidation();
		params = new ValidationParams();
		this.hash = hash;
		this.profile = profile;
	}

	@Override
	public void validateName(String name) throws ValidationException {
		logger.info("Validating name: " + name);
		
		nameValidationRules.setValidationRules();
		List<IValidation> rules = nameValidationRules.getValidationRules();
		params.setName(name);
		for (IValidation rule: rules) {
			if(!rule.isValid(params)) {
				logger.error("Name Validation Failed. Error: " + rule.getError());
				throw new ValidationException(rule.getError());
			}
		}
		logger.info("Validation Successful.");
	}

	@Override
	public void validatePassword(String password, String confirmPassword) throws ValidationException{
		logger.info("Validating Password");
		
		passwordValidationRules.setValidationRules();
		List<IValidation> rules = passwordValidationRules.getValidationRules();

		String hashedPassword = hash.generateHashValue(password);
		if (hashedPassword.equals(profile.getHashedPassword())) {
			String error = "New password cannot be same as the old password";
			logger.error("Password Validation Failed. Error: " + error);
			throw new ValidationException(error);
		}
		params.setPassword(password);
		params.setConfirmPassword(confirmPassword);
		for (IValidation rule: rules) {
			if(!rule.isValid(params)) {
				logger.error("Password Validation Failed. Error: " + rule.getError());
				throw new ValidationException(rule.getError());
			}
		}
		logger.info("Validation Successful.");
	}
}

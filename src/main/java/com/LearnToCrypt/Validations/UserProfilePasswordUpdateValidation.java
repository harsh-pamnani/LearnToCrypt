package com.LearnToCrypt.Validations;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.app.LearnToCryptApplication;

public class UserProfilePasswordUpdateValidation {
	private List<IValidation> validationRules;
    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	public UserProfilePasswordUpdateValidation() {
		validationRules = new ArrayList<IValidation>();
		setValidationRules();
	}

	public List<IValidation> getValidationRules() {
		logger.info("Getting user profile password validation rules.");
		return validationRules;
	}

	private void setValidationRules() {
		validationRules.add(new PasswordLengthValidation());
		validationRules.add(new PasswordLowerCaseValidation());
		validationRules.add(new PasswordSpecialCharValidation());
		validationRules.add(new PasswordUpperCaseValidation());

		validationRules.add(new ConfirmPasswordValidation());
		logger.info("User profile password validation rules created.");
	}
}

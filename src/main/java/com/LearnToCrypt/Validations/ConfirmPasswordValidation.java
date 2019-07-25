package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class ConfirmPasswordValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(ConfirmPasswordValidation.class);
	public static final String ERROR = "Confirm Password doesn't match.";

	@Override
	public void setValue(String ruleValue) {
		return;
	}
	
	@Override
	public boolean isValid(User user, String confirmPassword) {		
		boolean result = user.getPassword().equals(confirmPassword);
		logger.info("Confirm password validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

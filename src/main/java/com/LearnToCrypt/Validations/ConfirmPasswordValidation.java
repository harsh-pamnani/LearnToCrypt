package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfirmPasswordValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(ConfirmPasswordValidation.class);
	public static final String ERROR = "Confirm Password doesn't match.";

	@Override
	public void setValue(String ruleValue) {
		return;
	}
	
	@Override
	public boolean isValid(IValidationParams params) {
		String password = params.getPassword();
		String confirmPassword = params.getConfirmPassword();
		boolean result = password.equals(confirmPassword);
		logger.info("Confirm password validation. Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

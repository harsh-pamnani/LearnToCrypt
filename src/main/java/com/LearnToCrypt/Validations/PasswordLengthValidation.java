package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordLengthValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(PasswordLengthValidation.class);
	public static final String ERROR_START = "Password must be at least ";
	public static final String ERROR_END = " characters long.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(IValidationParams params) {
		String password = params.getPassword();
		boolean result = password.length() >= Integer.parseInt(this.ruleValue);
		logger.info("Password length validation. Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR_START + this.ruleValue + ERROR_END;
	}
	
}

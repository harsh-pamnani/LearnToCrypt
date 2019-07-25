package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

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
	public boolean isValid(User user, String confirmPassword) {
		boolean result = user.getPassword().length() >= Integer.parseInt(this.ruleValue);
		logger.info("Password length validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR_START + this.ruleValue + ERROR_END;
	}
	
}

package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.app.LearnToCryptApplication;

public class PasswordLengthValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	@Override
	public boolean isValid(User user, String confirmPassword) {
		boolean result = user.getPassword().length() > 7;
		logger.info("Password length validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return "Password must be at least 8 characters long.";
	}
	
}

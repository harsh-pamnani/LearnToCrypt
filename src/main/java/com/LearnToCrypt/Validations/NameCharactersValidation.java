package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class NameCharactersValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(NameCharactersValidation.class);

	@Override
	public boolean isValid(User user, String confirmPassword) {
		boolean result = user.getName().matches("[a-zA-Z ]+");
		logger.info("Name characters validation for user : " + user.getEmail() + ". Name : " + user.getName()
				+ ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return "Name can not contain any digits or special characters.";
	}
}

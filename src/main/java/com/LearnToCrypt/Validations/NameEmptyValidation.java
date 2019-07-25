package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class NameEmptyValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(NameEmptyValidation.class);
	public static final String ERROR = "Name can not be empty.";

	@Override
	public void setValue(String ruleValue) {
		return;
	}
	
	@Override
	public boolean isValid(User user, String confirmPassword) {
		boolean result = false;

		String username = user.getName();
		if (username != null) {
			result = !username.equals("");
			logger.info("Name empty validation for user : " + user.getEmail() + ". Result : " + result);
		}

		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NameEmptyValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(NameEmptyValidation.class);
	public static final String ERROR = "Name can not be empty.";

	@Override
	public void setValue(String ruleValue) {
		return;
	}
	
	@Override
	public boolean isValid(IValidationParams params) {
		boolean result = false;

		String username = params.getName();
		if (username != null) {
			result = !username.equals("");
			logger.info("Name empty validation for Name : " + username + ". Result : " + result);
		}

		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

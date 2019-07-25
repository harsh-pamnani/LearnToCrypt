package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class NameCharactersValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(NameCharactersValidation.class);
	public static final String ERROR = "Name can not contain any digits or special characters.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(User user, String confirmPassword) {
		boolean result = user.getName().matches(this.ruleValue);
		logger.info("Name characters validation for user : " + user.getEmail() + ". Name : " + user.getName()
				+ ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NameCharactersValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(NameCharactersValidation.class);
	public static final String ERROR = "Name can not contain any digits or special characters.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(IValidationParams params) {
		String name = params.getName();
		boolean result = name.matches(this.ruleValue);
		logger.info("Name characters validation for Name : " + name
				+ ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

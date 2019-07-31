package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordUpperCaseValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(PasswordUpperCaseValidation.class);
	public static final String ERROR = "Password must contain at least 1 uppercase letter.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
		
	@Override
	public boolean isValid(IValidationParams params) {
		String password = params.getPassword();
		Pattern upppcaseRegx = Pattern.compile(this.ruleValue);
		Matcher upperCaseMatcher = upppcaseRegx.matcher(password);
		
		boolean result = upperCaseMatcher.find();
		logger.info("Password uppercase validation. Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

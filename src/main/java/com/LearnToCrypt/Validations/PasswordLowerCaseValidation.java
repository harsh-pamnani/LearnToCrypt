package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordLowerCaseValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(PasswordLowerCaseValidation.class);
	public static final String ERROR = "Password must contain at least 1 lowercase letter.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
		
	@Override
	public boolean isValid(IValidationParams params) {
		String password = params.getPassword();
		Pattern lowercaseRegx = Pattern.compile(this.ruleValue);
		Matcher passwordLowerCaseMatcher = lowercaseRegx.matcher(password);
		
		boolean result = passwordLowerCaseMatcher.find();
		logger.info("Password lowercase validation. Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}

}

package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordSpecialCharValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(PasswordSpecialCharValidation.class);
	public static final String ERROR = "Password must contain at least 1 special charachter.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(IValidationParams params) {
		String password = params.getPassword();
		Pattern specialCharRegx = Pattern.compile(this.ruleValue);
		Matcher specialCharachterMatcher = specialCharRegx.matcher(password);
		
		boolean result = specialCharachterMatcher.find();
		logger.info("Password special character validation. Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

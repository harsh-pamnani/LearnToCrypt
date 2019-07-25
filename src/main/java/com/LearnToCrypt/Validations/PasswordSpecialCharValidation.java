package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordSpecialCharValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(PasswordSpecialCharValidation.class);
	public static final String ERROR = "Password must contain at least 1 special charachter.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(User user, String confirmPassword) {
		Pattern specialCharRegx = Pattern.compile(this.ruleValue);
		Matcher specialCharachterMatcher = specialCharRegx.matcher(user.getPassword());
		
		boolean result = specialCharachterMatcher.find();
		logger.info("Password special character validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

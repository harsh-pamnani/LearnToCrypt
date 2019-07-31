package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(EmailValidation.class);
	public static final String ERROR = "Email id is not valid.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(IValidationParams params) {
		// Reference for Email validation Regx -
		// https://stackoverflow.com/questions/8204680/java-regex-email
		String email = params.getEmail();
		Pattern validEmailRegx = Pattern.compile(this.ruleValue, Pattern.CASE_INSENSITIVE);
		Matcher emailMatcher = validEmailRegx.matcher(email);
		
		boolean result = emailMatcher.find();
		logger.info("Email validation for : " + email + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return ERROR;
	}
}

package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class EmailValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(EmailValidation.class);
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(User user, String confirmPassword) {
		// Reference for Email validation Regx -
		// https://stackoverflow.com/questions/8204680/java-regex-email
		Pattern validEmailRegx = Pattern.compile(this.ruleValue, Pattern.CASE_INSENSITIVE);
		Matcher emailMatcher = validEmailRegx.matcher(user.getEmail());
		
		boolean result = emailMatcher.find();
		logger.info("Email validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return "Email id is not valid.";
	}
}

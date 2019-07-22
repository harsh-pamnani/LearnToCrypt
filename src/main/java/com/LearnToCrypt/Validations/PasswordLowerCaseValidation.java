package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordLowerCaseValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(PasswordLowerCaseValidation.class);

	public static final Pattern LOWERCASE_REGEX = Pattern.compile(".*[a-z].*");
	
	@Override
	public boolean isValid(User user, String confirmPassword) {
		Matcher passwordLowerCaseMatcher = LOWERCASE_REGEX.matcher(user.getPassword());
		boolean result = passwordLowerCaseMatcher.find();
		logger.info("Password lowercase validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return "Password must contain at least 1 lowercase letter.";
	}

}

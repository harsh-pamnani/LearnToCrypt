package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.app.LearnToCryptApplication;

public class PasswordUpperCaseValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	public static final Pattern UPPERCASE_REGEX = Pattern.compile(".*[A-Z].*");
	
	@Override
	public boolean isValid(User user, String confirmPassword) {
		Matcher upperCaseMatcher = UPPERCASE_REGEX.matcher(user.getPassword());
		boolean result = upperCaseMatcher.find();
		logger.info("Password uppercase validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return "Password must contain at least 1 uppercase letter.";
	}
}

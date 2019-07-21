package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.app.LearnToCryptApplication;

public class PasswordSpecialCharValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	public static final Pattern SPECIAL_CHAR_REGEX = Pattern.compile(".*[!@#$%^&*()].*");

	@Override
	public boolean isValid(User user, String confirmPassword) {
		Matcher specialCharachterMatcher = SPECIAL_CHAR_REGEX.matcher(user.getPassword());
		boolean result = specialCharachterMatcher.find();
		logger.info("Password special character validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return "Password must contain at least 1 special charachter.";
	}
}

package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class EmailValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(EmailValidation.class);

	// Reference for Email validation Regx -
	// https://stackoverflow.com/questions/8204680/java-regex-email
	public static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public boolean isValid(User user, String confirmPassword) {
		Matcher emailMatcher = VALID_EMAIL_REGEX.matcher(user.getEmail());
		boolean result = emailMatcher.find();
		logger.info("Email validation for user : " + user.getEmail() + ". Result : " + result);
		return result;
	}

	@Override
	public String getError() {
		return "Email id is not valid.";
	}
}

package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.LearnToCrypt.BusinessModels.IUser;

public class EmailValidation implements IValidation {
	
	public static final Pattern VALID_EMAIL_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	@Override
	public boolean isValid(IUser user, String confirmPassword) {
		// Email validation Regx taken from https://stackoverflow.com/questions/8204680/java-regex-email
		Matcher emailMatcher = VALID_EMAIL_REGEX.matcher(user.getEmail());
		
		return emailMatcher.find();
	}

	@Override
	public String getError() {
		return "Email id is not valid.";
	}
}

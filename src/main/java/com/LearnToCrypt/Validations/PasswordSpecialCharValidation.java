package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordSpecialCharValidation implements IValidation {

	public static final Pattern SPECIAL_CHAR_REGEX = Pattern.compile(".*[!@#$%^&*()].*");
	
	@Override
	public boolean isValid(User user, String confirmPassword) {
		Matcher upperCaseMatcher = SPECIAL_CHAR_REGEX.matcher(user.getPassword());
		
		return upperCaseMatcher.find();
	}

	@Override
	public String getError() {
		return "Password must contain at least 1 special charachter.";
	}
}

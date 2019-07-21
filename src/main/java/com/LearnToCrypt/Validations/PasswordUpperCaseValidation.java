package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.LearnToCrypt.BusinessModels.IUser;

public class PasswordUpperCaseValidation implements IValidation {

	public static final Pattern UPPERCASE_REGEX = Pattern.compile(".*[A-Z].*");
	
	@Override
	public boolean isValid(IUser user, String confirmPassword) {
		Matcher upperCaseMatcher = UPPERCASE_REGEX.matcher(user.getPassword());
		
		return upperCaseMatcher.find();
	}

	@Override
	public String getError() {
		return "Password must contain at least 1 uppercase letter.";
	}
}

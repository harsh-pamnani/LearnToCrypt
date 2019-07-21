package com.LearnToCrypt.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.LearnToCrypt.BusinessModels.IUser;

public class PasswordLowerCaseValidation implements IValidation {

	public static final Pattern LOWERCASE_REGEX = Pattern.compile(".*[a-z].*");
	
	@Override
	public boolean isValid(IUser user, String confirmPassword) {
		Matcher passwordLowerCaseMatcher = LOWERCASE_REGEX.matcher(user.getPassword());
		
		return passwordLowerCaseMatcher.find();
	}

	@Override
	public String getError() {
		return "Password must contain at least 1 lowercase letter.";
	}

}

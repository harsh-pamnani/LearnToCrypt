package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordLengthValidation implements IValidation {

	@Override
	public boolean isValid(User user, String confirmPassword) {
		return user.getPassword().length() > 7;
	}

	@Override
	public String getError() {
		return "Password must be at least 8 characters long.";
	}
	
}

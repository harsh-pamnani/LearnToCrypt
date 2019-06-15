package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.User;

public class ConfirmPasswordValidation implements IValidation {

	@Override
	public boolean isValid(User user, String confirmPassword) {
		return user.getPassword().equals(confirmPassword);
	}

	@Override
	public String getError() {
		return "Confirm Password doesn't match.";
	}

}

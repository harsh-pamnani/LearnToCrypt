package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.IUser;

public class ConfirmPasswordValidation implements IValidation {

	@Override
	public boolean isValid(IUser user, String confirmPassword) {
		return user.getPassword().equals(confirmPassword);
	}

	@Override
	public String getError() {
		return "Confirm Password doesn't match.";
	}

}

package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.User;

public class NameEmptyValidation implements IValidation {

	@Override
	public boolean isValid(User user, String confirmPassword) {
		return !user.getName().equals("");
	}

	@Override
	public String getError() {
		return "Name can not be empty.";
	}

}

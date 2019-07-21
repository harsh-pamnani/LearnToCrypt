package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.IUser;

public class NameCharactersValidation implements IValidation {

	@Override
	public boolean isValid(IUser user, String confirmPassword) {
		return user.getName().matches("[a-zA-Z ]+");
	}

	@Override
	public String getError() {
		return "Name can not contain any digits or special characters.";
	}

}

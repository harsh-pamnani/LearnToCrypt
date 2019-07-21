package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.IUser;

public class NameEmptyValidation implements IValidation {

	@Override
	public boolean isValid(IUser user, String confirmPassword) {
		String username = user.getName();
		if(username != null) {
			return !username.equals("");
		}
		return false;
	}

	@Override
	public String getError() {
		return "Name can not be empty.";
	}

}

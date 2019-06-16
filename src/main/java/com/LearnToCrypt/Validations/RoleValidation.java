package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.User;

public class RoleValidation implements IValidation {

	@Override
	public boolean isValid(User user, String confirmPassword) {
		String role = user.getRole();
		if( role != null) {
			return role.equals("Student") || role.equals("Instructor");
		}
		return false;
	}

	@Override
	public String getError() {
		return "Role can not be empty.";
	}
	
}

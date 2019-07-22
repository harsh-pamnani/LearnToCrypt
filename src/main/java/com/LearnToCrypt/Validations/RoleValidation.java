package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class RoleValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(RoleValidation.class);

	@Override
	public boolean isValid(User user, String confirmPassword) {
		String role = user.getRole();
		if (role != null) {
			boolean result = role.equals("Student") || role.equals("Instructor");
			logger.info("Role validation for user : " + user.getEmail() + ". Role : " + user.getRole() + ". Name : "
					+ user.getName() + ". Result : " + result);
			return result;
		}
		return false;
	}

	@Override
	public String getError() {
		return "Role can not be empty.";
	}

}

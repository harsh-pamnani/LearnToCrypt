package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;

public class RoleValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(RoleValidation.class);
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(User user, String confirmPassword) {
		String role = user.getRole();
		if (role != null && !role.equals("")) {
			boolean result = this.ruleValue.contains(role);
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

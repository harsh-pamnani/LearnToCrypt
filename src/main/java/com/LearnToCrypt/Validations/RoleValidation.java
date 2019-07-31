package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(RoleValidation.class);
	public static final String ERROR = "Role can not be empty.";
	private String ruleValue;
	
	@Override
	public void setValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Override
	public boolean isValid(IValidationParams params) {
		String role = params.getRole();
		if (role != null && !role.equals("")) {
			boolean result = this.ruleValue.contains(role);
			logger.info("Role validation for Role : " + role + ". Result : " + result);
			return result;
		}
		return false;
	}

	@Override
	public String getError() {
		return ERROR;
	}

}

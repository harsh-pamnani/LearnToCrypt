package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.User;

public interface IValidation {
	public void setValue(String ruleValue);
	
	public abstract boolean isValid(User user, String confirmPassword);
	
	public abstract String getError();
}

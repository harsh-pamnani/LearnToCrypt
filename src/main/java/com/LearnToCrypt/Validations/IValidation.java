package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.User;

public interface IValidation {
	
	public boolean isValid(User user, String confirmPassword);
	
	public String getError();
}

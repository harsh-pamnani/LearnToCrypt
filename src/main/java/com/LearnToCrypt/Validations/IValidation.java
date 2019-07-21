package com.LearnToCrypt.Validations;

import com.LearnToCrypt.BusinessModels.IUser;

public interface IValidation {
	
	public boolean isValid(IUser user, String confirmPassword);
	
	public String getError();
}

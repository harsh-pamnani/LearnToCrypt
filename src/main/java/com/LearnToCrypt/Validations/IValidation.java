package com.LearnToCrypt.Validations;

public interface IValidation {
	public void setValue(String ruleValue);
	
	public abstract boolean isValid(IValidationParams params);

	public abstract String getError();
}

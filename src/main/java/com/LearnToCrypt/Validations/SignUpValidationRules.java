package com.LearnToCrypt.Validations;

import java.util.ArrayList;

public class SignUpValidationRules {
	private ArrayList<IValidation> validationRules;

	public SignUpValidationRules() {
		validationRules = new ArrayList<IValidation>();
	}
	
	public ArrayList<IValidation> getValidationRules() {
		return validationRules;
	}

	public void setValidationRules() {
		validationRules.add(new NameValidation());
		
		validationRules.add(new EmailValidation());
		
		validationRules.add(new PasswordLengthValidation());
		validationRules.add(new PasswordLowerCaseValidation());
		validationRules.add(new PasswordSpecialCharValidation());
		validationRules.add(new PasswordUpperCaseValidation());
		
		validationRules.add(new ConfirmPasswordValidation());
		
		validationRules.add(new RoleValidation());
	}
}

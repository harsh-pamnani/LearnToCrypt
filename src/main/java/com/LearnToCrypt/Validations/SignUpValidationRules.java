package com.LearnToCrypt.Validations;

import java.util.ArrayList;

public class SignUpValidationRules {
	private ArrayList<IValidation> validationRules;

	public SignUpValidationRules() {
		validationRules = new ArrayList<IValidation>();
		setValidationRules();
	}
	
	public ArrayList<IValidation> getValidationRules() {
		return validationRules;
	}

	private void setValidationRules() {
		validationRules.add(new NameEmptyValidation());
		validationRules.add(new NameCharactersValidation());
		
		validationRules.add(new EmailValidation());
		
		validationRules.add(new PasswordLengthValidation());
		validationRules.add(new PasswordLowerCaseValidation());
		validationRules.add(new PasswordSpecialCharValidation());
		validationRules.add(new PasswordUpperCaseValidation());
		
		validationRules.add(new ConfirmPasswordValidation());
		
		validationRules.add(new RoleValidation());
	}
}

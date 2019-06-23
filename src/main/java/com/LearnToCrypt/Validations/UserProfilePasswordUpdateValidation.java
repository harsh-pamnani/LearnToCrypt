package com.LearnToCrypt.Validations;

import java.util.ArrayList;
import java.util.List;

public class UserProfilePasswordUpdateValidation {
	private List<IValidation> validationRules;

	public UserProfilePasswordUpdateValidation() {
		validationRules = new ArrayList<IValidation>();
		setValidationRules();
	}

	public List<IValidation> getValidationRules() {
		return validationRules;
	}

	private void setValidationRules() {
		validationRules.add(new PasswordLengthValidation());
		validationRules.add(new PasswordLowerCaseValidation());
		validationRules.add(new PasswordSpecialCharValidation());
		validationRules.add(new PasswordUpperCaseValidation());

		validationRules.add(new ConfirmPasswordValidation());
	}
}

package com.LearnToCrypt.SignUp;

import java.util.List;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.Validations.IValidation;
import com.LearnToCrypt.Validations.SignUpValidationRules;

public class ValidateSignUpForm {
	
	private SignUpValidationRules validationRules = null;
	
	public ValidateSignUpForm() {
		validationRules = new SignUpValidationRules();
	}
	
	public String validateFormDetails(User user, String confirmPassword) {
		String formError = "";
		
		List<IValidation> rules = validationRules.getValidationRules();
		
		for (IValidation rule: rules) {
			if(!rule.isValid(user, confirmPassword)) {
				formError = rule.getError();
				break;
			}
		}
		
		return formError;
	}
}

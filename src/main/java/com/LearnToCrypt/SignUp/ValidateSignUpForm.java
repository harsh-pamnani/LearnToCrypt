package com.LearnToCrypt.SignUp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.Validations.IValidation;
import com.LearnToCrypt.Validations.SignUpValidationRules;

public class ValidateSignUpForm {
	
	private SignUpValidationRules validationRules;
	private static final Logger logger = LogManager.getLogger(ValidateSignUpForm.class);

	public ValidateSignUpForm() {
		validationRules = new SignUpValidationRules();
	}
	
	public void validateFormDetails(User user, String confirmPassword) throws SignUpFailureException {
		validationRules.setValidationRules();
		List<IValidation> rules = validationRules.getValidationRules();
		
		for (IValidation rule: rules) {
			if(!rule.isValid(user, confirmPassword)) {
				String error = rule.getError();
				logger.error("Registration failed with error : " + error);
				throw new SignUpFailureException(error);
			}
		}
	}
}

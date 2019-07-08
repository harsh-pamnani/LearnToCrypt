package com.LearnToCrypt.SignUp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.SignIn.LoginController;
import com.LearnToCrypt.Validations.IValidation;
import com.LearnToCrypt.Validations.SignUpValidationRules;

public class ValidateSignUpForm {
	
	private SignUpValidationRules validationRules = null;
	private static final Logger logger = LogManager.getLogger(LoginController.class);

	public ValidateSignUpForm() {
		validationRules = new SignUpValidationRules();
	}
	
	public String validateFormDetails(User user, String confirmPassword) {
		String formError = "";
		
		List<IValidation> rules = validationRules.getValidationRules();
		
		for (IValidation rule: rules) {
			if(!rule.isValid(user, confirmPassword)) {
				formError = rule.getError();
				logger.error("Registration failed with error : " + formError);
				break;
			}
		}
		
		return formError;
	}
}

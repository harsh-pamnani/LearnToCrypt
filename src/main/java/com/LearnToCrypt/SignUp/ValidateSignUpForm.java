package com.LearnToCrypt.SignUp;

import java.util.List;

import com.LearnToCrypt.Validations.IValidationParams;
import com.LearnToCrypt.Validations.ValidationParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.Validations.IValidation;
import com.LearnToCrypt.Validations.SignUpValidationRules;

public class ValidateSignUpForm {

	private IValidationParams params;
	private SignUpValidationRules validationRules;
	private static final Logger logger = LogManager.getLogger(ValidateSignUpForm.class);

	public ValidateSignUpForm() {
		validationRules = new SignUpValidationRules();
		params = new ValidationParams();
	}
	
	public void validateFormDetails(User user, String confirmPassword) throws SignUpFailureException {
		validationRules.setValidationRules();
		List<IValidation> rules = validationRules.getValidationRules();
		params.setName(user.getName());
		params.setEmail(user.getEmail());
		params.setPassword(user.getPassword());
		params.setConfirmPassword(confirmPassword);
		params.setRole(user.getRole());

		for (IValidation rule: rules) {
			if(!rule.isValid(params)) {
				String error = rule.getError();
				logger.error("Registration failed with error : " + error);
				throw new SignUpFailureException(error);
			}
		}
	}
}

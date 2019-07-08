package com.LearnToCrypt.Validations;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.app.LearnToCryptApplication;

public class SignUpValidationRules {
	private List<IValidation> validationRules;
    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	public SignUpValidationRules() {
		validationRules = new ArrayList<IValidation>();
		setValidationRules();
	}
	
	public List<IValidation> getValidationRules() {
		logger.info("Getting sign up validation rules.");
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
		
		logger.info("Sign up validation rules created.");
	}
}

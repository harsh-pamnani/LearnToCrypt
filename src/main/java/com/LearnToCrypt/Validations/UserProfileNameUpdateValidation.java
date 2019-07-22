package com.LearnToCrypt.Validations;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserProfileNameUpdateValidation {

	private List<IValidation> validationRules;
    private static final Logger logger = LogManager.getLogger(UserProfileNameUpdateValidation.class);

	public UserProfileNameUpdateValidation() {
		validationRules = new ArrayList<IValidation>();
		setValidationRules();
	}

	public List<IValidation> getValidationRules() {
		logger.info("Getting user profile name validation rules.");
		return validationRules;
	}

	private void setValidationRules() {
		validationRules.add(new NameEmptyValidation());
		validationRules.add(new NameCharactersValidation());
		
		logger.info("User profile name validation rules created.");
	}
}

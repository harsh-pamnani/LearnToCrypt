package com.LearnToCrypt.Validations;

import java.util.ArrayList;
import java.util.List;

public class UserProfileNameUpdateValidation {

	private List<IValidation> validationRules;

	public UserProfileNameUpdateValidation() {
		validationRules = new ArrayList<IValidation>();
		setValidationRules();
	}

	public List<IValidation> getValidationRules() {
		return validationRules;
	}

	private void setValidationRules() {
		validationRules.add(new NameEmptyValidation());
		validationRules.add(new NameCharactersValidation());
	}
}

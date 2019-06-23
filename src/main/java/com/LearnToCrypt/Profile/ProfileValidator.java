package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.Validations.IValidation;
import com.LearnToCrypt.Validations.UserProfileNameUpdateValidation;
import com.LearnToCrypt.Validations.UserProfilePasswordUpdateValidation;

import java.util.List;

public class ProfileValidator implements IProfileValidator {

	private UserProfileNameUpdateValidation nameValidationRules = null;
	private UserProfilePasswordUpdateValidation passwordValidationRules = null;
	private User user;

	public ProfileValidator(IUserProfileBridge profile) {
		nameValidationRules = new UserProfileNameUpdateValidation();
		passwordValidationRules = new UserProfilePasswordUpdateValidation();
		user = profile.getUser();
	}

	@Override
	public String isNameValid(String name) {
		List<IValidation> rules = nameValidationRules.getValidationRules();
		String formError = null;
		for (IValidation rule: rules) {
			if(!rule.isValid(user, "")) {
				formError = rule.getError();
				break;
			}
		}
		return formError;
	}

	@Override
	public String isPasswordValid(String password, String confirmPassword) {
		List<IValidation> rules = passwordValidationRules.getValidationRules();
		String formError = null;
		user.setPassword(password);
		for (IValidation rule: rules) {
			if(!rule.isValid(user, confirmPassword)) {
				formError = rule.getError();
				break;
			}
		}
		return formError;
	}
}

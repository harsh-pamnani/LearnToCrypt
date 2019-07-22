package com.LearnToCrypt.Validations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IValidationRulesDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserProfilePasswordUpdateValidation {
	private List<IValidation> validationRules;
    private static final Logger logger = LogManager.getLogger(UserProfilePasswordUpdateValidation.class);
	DAOAbstractFactory daoAbstractFactory;
	private Map<String, IValidation> rulesMap = new HashMap<String, IValidation>();

	public UserProfilePasswordUpdateValidation() {
		daoAbstractFactory = new DAOAbstractFactory();
		validationRules = new ArrayList<IValidation>();

		setRulesMap();
		setValidationRules();
	}

	private void setRulesMap() {
		rulesMap.put("PasswordLengthValidation", new PasswordLengthValidation());
		rulesMap.put("PasswordLowerCaseValidation", new PasswordLowerCaseValidation());
		rulesMap.put("PasswordSpecialCharValidation", new PasswordSpecialCharValidation());
		rulesMap.put("PasswordUpperCaseValidation", new PasswordUpperCaseValidation());
		rulesMap.put("ConfirmPasswordValidation", new ConfirmPasswordValidation());
	}

	public List<IValidation> getValidationRules() {
		logger.info("Getting user profile password validation rules.");
		return validationRules;
	}

	private void setValidationRules() {
		IValidationRulesDAO profilePasswordUpdateValidationRulesDAO = daoAbstractFactory.createProfilePasswordUpdateValidationRulesDAO();

		Map<String, String> rulesAndValue = profilePasswordUpdateValidationRulesDAO.getRulesAndValues();

		try {
			for(String key: rulesAndValue.keySet()) {
				IValidation validationRule = rulesMap.get(key);
				validationRule.setValue(rulesAndValue.get(key));
				validationRules.add(validationRule);
			}
		} catch (NullPointerException e) {
			logger.error("Error in creating the profile update password validation rules. ", e);
		}

		logger.info("User profile password update validation rules created.");
	}
}

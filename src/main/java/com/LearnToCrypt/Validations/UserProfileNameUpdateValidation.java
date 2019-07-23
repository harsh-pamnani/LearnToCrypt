package com.LearnToCrypt.Validations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IValidationRulesDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserProfileNameUpdateValidation {

	private List<IValidation> validationRules;
    private static final Logger logger = LogManager.getLogger(UserProfileNameUpdateValidation.class);
	DAOAbstractFactory daoAbstractFactory;
	private Map<String, IValidation> rulesMap = new HashMap<String, IValidation>();

	public UserProfileNameUpdateValidation() {
		daoAbstractFactory = new DAOAbstractFactory();
		validationRules = new ArrayList<IValidation>();

		setRulesMap();
	}

	private void setRulesMap() {
		rulesMap.put("NameCharactersValidation", new NameCharactersValidation());
	}

	public List<IValidation> getValidationRules() {
		logger.info("Getting user profile name validation rules.");
		return validationRules;
	}

	public void setValidationRules() {
		IValidationRulesDAO profileNameUpdateValidationRulesDAO = daoAbstractFactory.createProfileNameUpdateValidationRulesDAO();

		Map<String, String> rulesAndValue = profileNameUpdateValidationRulesDAO.getRulesAndValues();

		try {
			for(String key: rulesAndValue.keySet()) {
				IValidation validationRule = rulesMap.get(key);
				
				IValidationRulesDAO profileNameRuleValueDAO = daoAbstractFactory.createProfileNameUpdateValidationRulesDAO();
				String ruleValue = profileNameRuleValueDAO.getRulesValue(key);
				validationRule.setValue(ruleValue);
				
				validationRules.add(validationRule);
			}
		} catch (NullPointerException e) {
			logger.error("Error in creating the profile name update validation rules. ", e);
		}

		logger.info("User profile name update validation rules created.");
	}
}

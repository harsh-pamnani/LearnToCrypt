package com.LearnToCrypt.Validations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IValidationRulesDAO;

public class SignUpValidationRules {
	private List<IValidation> validationRules;
    private static final Logger logger = LogManager.getLogger(SignUpValidationRules.class);
    DAOAbstractFactory daoAbstractFactory;
    private Map<String, IValidation> rulesMap = new HashMap<String, IValidation>();
    
	public SignUpValidationRules() {
		daoAbstractFactory = new DAOAbstractFactory();
		validationRules = new ArrayList<IValidation>();
		
		setRulesMap();
		setValidationRules();
	}
	
	public List<IValidation> getValidationRules() {
		logger.info("Getting sign up validation rules.");
		return validationRules;
	}

	private void setRulesMap() {
		rulesMap.put("EmailValidation", new EmailValidation());
		rulesMap.put("NameCharactersValidation", new NameCharactersValidation());
		rulesMap.put("PasswordLengthValidation", new PasswordLengthValidation());
		rulesMap.put("PasswordLowerCaseValidation", new PasswordLowerCaseValidation());
		rulesMap.put("PasswordSpecialCharValidation", new PasswordSpecialCharValidation());
		rulesMap.put("PasswordUpperCaseValidation", new PasswordUpperCaseValidation());
		rulesMap.put("ConfirmPasswordValidation", new ConfirmPasswordValidation());
		rulesMap.put("RoleValidation", new RoleValidation());
	}
	
	private void setValidationRules() {
		IValidationRulesDAO signUpValidationRulesDAO = daoAbstractFactory.createSignUpValidationRulesDAO();
		
		Map<String, String> rulesAndValue = signUpValidationRulesDAO.getRulesAndValues();
		
		try {
			for(String key: rulesAndValue.keySet()) {
				IValidation validationRule = rulesMap.get(key);
				validationRule.setValue(rulesAndValue.get(key));
				validationRules.add(validationRule);
			}
		} catch (NullPointerException e) {
			logger.error("Error in creating the sign up validation rules. ", e);
		}
		
		logger.info("Sign up validation rules created.");
	}
}

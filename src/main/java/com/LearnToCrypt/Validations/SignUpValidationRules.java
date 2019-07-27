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
	
	public void setValidationRules() {
		IValidationRulesDAO signUpValidationRulesDAO = daoAbstractFactory.createSignUpValidationRulesDAO();
		List<String> rules = signUpValidationRulesDAO.getRules();
		
		for(String rule: rules) {
			IValidation validationRule = rulesMap.get(rule);
			String ruleValue = signUpValidationRulesDAO.getRulesValue(rule);
			validationRule.setValue(ruleValue);
			validationRules.add(validationRule);
		}

		logger.info("Sign up validation rules created.");
	}
}

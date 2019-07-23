package com.LearnToCrypt.DAO;

import java.util.Map;

public interface IValidationRulesDAO {
	public Map<String, String> getRulesAndValues();
	public String getRulesValue(String ruleName);
}

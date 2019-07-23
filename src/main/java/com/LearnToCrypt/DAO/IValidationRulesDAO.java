package com.LearnToCrypt.DAO;

import java.util.List;

public interface IValidationRulesDAO {
	public List<String> getRulesAndValues();
	public String getRulesValue(String ruleName);
}

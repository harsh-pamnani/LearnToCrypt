package com.LearnToCrypt.DAO;

import java.util.List;

public interface IValidationRulesDAO {
	public List<String> getRules();
	public String getRulesValue(String ruleName);
}

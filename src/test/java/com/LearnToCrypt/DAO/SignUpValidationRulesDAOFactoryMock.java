package com.LearnToCrypt.DAO;

import com.LearnToCrypt.DatabaseConnection.DBConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpValidationRulesDAOFactoryMock implements IValidationRulesDAO {

    private List<String> rules;
    private Map<String, String> rulesMap;

    public SignUpValidationRulesDAOFactoryMock() {
        rules = new ArrayList<String>();
        rulesMap = new HashMap<String, String>();
        loadRulesMap();
    }

    @Override
    public List<String> getRules() {
        for(String key: rulesMap.keySet()) {
            rules.add(key);
        }
        return rules;
    }

    @Override
    public String getRulesValue(String ruleName) {
        return rulesMap.get(ruleName);
    }

    public void loadRulesMap() {
        rulesMap.put("ConfirmPasswordValidation", "Yes");
        rulesMap.put("EmailValidation", "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
        rulesMap.put("NameCharactersValidation", "[a-zA-Z ]+");
        rulesMap.put("PasswordLengthValidation", "8");
        rulesMap.put("PasswordLowerCaseValidation", ".*[a-z].*");
        rulesMap.put("PasswordSpecialCharValidation", ".*[!@#$%^&*()].*");
        rulesMap.put("PasswordUpperCaseValidation", ".*[A-Z].*");
        rulesMap.put("RoleValidation", "Student Instructor");
    }
}

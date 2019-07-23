package com.LearnToCrypt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.DatabaseConnection.DBConnection;

public class SignUpValidationRulesDAO implements IValidationRulesDAO {

	DBConnection dbConnectionInstance = null;
	Connection dbConnection = null;
	private PreparedStatement statement;
	ResultSet resultSet;

	private static final Logger logger = LogManager.getLogger(SignUpValidationRulesDAO.class);
	private Map<String, String> rulesAndValues = new HashMap<String, String>();
	
	public SignUpValidationRulesDAO() {
		dbConnectionInstance = DBConnection.instance();
		loadRulesFromDB();
	}
	
	private void loadRulesFromDB() {
		String query = "CALL get_sign_up_validation_rules();";
		
		try {
			dbConnection = dbConnectionInstance.getConnection();
			
			statement = dbConnection.prepareStatement(query);		
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				rulesAndValues.put(resultSet.getString(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			logger.error("Error in loading sign up validation rules. ", e);
		} finally {
			dbConnectionInstance.closeConnection();
		}
	}
	
	@Override
	public Map<String, String> getRulesAndValues() {
		return rulesAndValues;
	}
	
	@Override
	public String getRulesValue(String ruleName) {
		String ruleValue = "";
		String query = "CALL get_sign_up_rules_value(\"" + ruleName + "\");";
		
		try {
			dbConnection = dbConnectionInstance.getConnection();
			
			statement = dbConnection.prepareStatement(query);		
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ruleValue = resultSet.getString(1);
			}
		} catch (SQLException e) {
			logger.error("Error in getting the value for sign up validation rule name : " + ruleName, e);
		} finally {
			dbConnectionInstance.closeConnection();
		}
		
		return ruleValue;
	}
}

package com.LearnToCrypt.DAO;

import com.LearnToCrypt.DatabaseConnection.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProfilePasswordUpdateValidationRulesDAO implements IValidationRulesDAO {

    DBConnection dbConnectionInstance = null;
    Connection dbConnection = null;
    private PreparedStatement statement;
    ResultSet resultSet;

    private static final Logger logger = LogManager.getLogger(SignUpValidationRulesDAO.class);
    private Map<String, String> rulesAndValues = new HashMap<String, String>();

    public ProfilePasswordUpdateValidationRulesDAO() {
        dbConnectionInstance = DBConnection.instance();
        loadRulesFromDB();
    }

    private void loadRulesFromDB() {
        String query = "CALL get_profile_password_update_validation_rules();";

        try {
            dbConnection = dbConnectionInstance.getConnection();

            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                rulesAndValues.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            logger.error("Error in loading profile password update validation rules. ", e);
        } finally {
            dbConnectionInstance.closeConnection();
        }
    }

    @Override
    public Map<String, String> getRulesAndValues() {
        return rulesAndValues;
    }
}
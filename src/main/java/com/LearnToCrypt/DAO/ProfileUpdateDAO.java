package com.LearnToCrypt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.DatabaseConnection.DBConnection;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import com.LearnToCrypt.app.LearnToCryptApplication;

public class ProfileUpdateDAO implements IPasswordUpdaterDAO, INameSetterDAO {

    DBConnection dbConnectionInstance = null;
    Connection dbConnection = null;
    private PreparedStatement statement;
    ResultSet resultSet;
    MD5 md5Algorithm;
    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

    public ProfileUpdateDAO() {
        dbConnectionInstance = DBConnection.instance();
        md5Algorithm = new MD5();
    }

    @Override
    public void setPassword(String email, String password) {
        logger.info("Updating Database. Setting Password: " + password);
        String hashedPassword = md5Algorithm.generateMD5HashValue(password);
        String query = "CALL update_password(\""+ email +"\", \""+ hashedPassword + "\");";
        updateDatabase(query);
    }

    @Override
    public void setResetToken(String email, String token) {
        logger.info("Updating Database. Setting token: " + token + " for email: " + email);
        String query = "CALL set_pass_reset(\""+ email +"\", \""+ token + "\");";
        updateDatabase(query);
    }

    @Override
    public String getEmailFromToken(String token) {
        logger.info("Querying Database. Getting email for token: " + token);
        String query = "CALL get_pass_reset(\""+ token + "\");";
        String email = null;
        try {
            dbConnection = dbConnectionInstance.getConnection();
            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                email = resultSet.getString(1);
            }
            logger.info("Queried Database. Email: " + email);
            return email;
        } catch (SQLException e) {
            logger.error("Database query error: " + e.getMessage());
        } finally {
            dbConnectionInstance.closeConnection();
        }
        return email;
    }

    @Override
    public void setName(String email, String name) {
        logger.info("Updating Database. Setting name: " + name + " for email: " + email);
        String query = "CALL set_username(\""+ email +"\", \""+ name + "\");";
        updateDatabase(query);
    }

    private void updateDatabase(String query) {
        try {
            dbConnection = dbConnectionInstance.getConnection();
            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
            logger.info("Updated Database");
        } catch (SQLException e) {
            logger.error("Database update error: " + e.getMessage());
        } finally {
            dbConnectionInstance.closeConnection();
        }
    }
}

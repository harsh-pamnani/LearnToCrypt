package com.LearnToCrypt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.LearnToCrypt.HashingAlgorithm.MD5;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DatabaseConnection.DBConnection;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAO implements IUserDAO {

	DBConnection dbConnectionInstance = null;
	Connection dbConnection = null;
	private PreparedStatement statement;
	ResultSet resultSet;
	MD5 md5Algorithm;

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	
	public UserDAO() {
		dbConnectionInstance = DBConnection.instance();
		md5Algorithm = new MD5();
	}
	
	@Override
	public void createUser(User user) {
		int role = ( user.getRole().equals("Student") ) ? 1 : 2;
		
		String hashedPassword = md5Algorithm.generateMD5HashValue(user.getPassword());
		String query = "CALL create_user(\""+user.getEmail()+"\", \""+ user.getName() + "\", \"" + hashedPassword + "\", "+ role + ");";
		
		try {
			dbConnection = dbConnectionInstance.getConnection();
			
			statement = dbConnection.prepareStatement(query);		
			resultSet = statement.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Error in creating a new user.");
			System.out.println("Error : " + e.getMessage());
			logger.error("Error in creating a new user.");
		} finally {
			dbConnectionInstance.closeConnection();
		}
	}
	
	@Override
	public boolean isUserValid(User user) {
		
		boolean isValid = false;
		
		String hashedPassword = md5Algorithm.generateMD5HashValue(user.getPassword());
		String query = "CALL count_user(\""+ user.getEmail() + "\",\"" + hashedPassword + "\");";
		
		try {
			isValid = isRegistered(isValid, query);
		} catch (SQLException e) {
			System.out.println("Error in fetching the user credentials");
			System.out.println("Error : " + e.getMessage());
			logger.error("Error in fetching the user credentials.");
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return isValid;
	}
	
	@Override
	public boolean isUserRegistered(User user) {		
		boolean isRegistered = false;
		
		String query = "CALL count_registered_user(\""+ user.getEmail() + "\");";
		
		try {
			isRegistered = isRegistered(isRegistered, query);
		} catch (SQLException e) {
			System.out.println("Error in fetching the user registration details");
			System.out.println("Error : " + e.getMessage());
			logger.error("Error in fetching the user registration details.");
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return isRegistered;
	}

	private boolean isRegistered(boolean isRegistered, String query) throws SQLException {
		dbConnection = dbConnectionInstance.getConnection();

		statement = dbConnection.prepareStatement(query);
		resultSet = statement.executeQuery();

		int userCount = 0;
		while (resultSet.next()) {
			userCount = resultSet.getInt(1);
		}

		if (userCount != 0) {
			isRegistered = true;
		}
		return isRegistered;
	}

	@Override
	public String getUserName(String email) {
		String userName = "";
		
		String query = "CALL get_user_name(\""+ email + "\");";
		
		try {
			dbConnection = dbConnectionInstance.getConnection();
			
			statement = dbConnection.prepareStatement(query);
			resultSet = statement.executeQuery();
						
			while (resultSet.next()) {
				userName = resultSet.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Error in fetching the user registration details");
			System.out.println("Error : " + e.getMessage());
			logger.error("Error in fetching the user registration details.");
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return userName;
	}
}

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
			logger.error("Error in creating a new user: " + user.getEmail(), e);
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
			isValid = isRegistered(query);
		} catch (SQLException e) {
			logger.error("Error in fetching the user credentials for user: " + user.getEmail(), e);
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return isValid;
	}
	
	@Override
	public boolean deleteUser(String email) {
		boolean isDeleted = false;
		
		String query = "CALL delete_user(\""+ email + "\");";
		
		try {
			dbConnection = dbConnectionInstance.getConnection();
			
			statement = dbConnection.prepareStatement(query);		
			resultSet = statement.executeQuery();
			isDeleted = true;
		} catch (SQLException e) {
			logger.error("Error in deleting the user user: " + email, e);
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return isDeleted;
	}
	
	@Override
	public boolean isUserRegistered(String email) {		
		boolean isRegistered = false;
		
		String query = "CALL count_registered_user(\""+ email + "\");";
		
		try {
			isRegistered = isRegistered(query);
		} catch (SQLException e) {
			logger.error("Error in fetching the user registration details for user: " + email, e);
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return isRegistered;
	}
	
	@Override
	public String getUserRole(String email) {
		String role = "";
		
		String query = "CALL get_user_role(\""+ email + "\");";
		
		try {
			dbConnection = dbConnectionInstance.getConnection();
			
			statement = dbConnection.prepareStatement(query);
			resultSet = statement.executeQuery();
						
			while (resultSet.next()) {
				role = resultSet.getString(1);
			}
		} catch (SQLException e) {
			logger.error("Error in fetching the user role.", e);
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return role;
	}

	@Override
	public String[] getProgress(String email) {
		String query = "CALL get_user_progress(\""+ email + "\");";
		String resultArray[] = null;
		try {
			dbConnection = dbConnectionInstance.getConnection();
			statement = dbConnection.prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if(resultSet.getString(1) != null){
					resultArray =resultSet.getString(1).split(",");
				}else {
					return null;
				}
			}
		}catch (SQLException e){
			logger.error("Error in fetching the user progress for user: " + email, e);
		}finally {
			dbConnectionInstance.closeConnection();
		}

		return resultArray;
	}

	@Override
	public void updateProgress(String email, String newProgress) {
		String[] userProgress = getProgress(email);
		boolean isAllowedToUpdate = false;
		if(userProgress != null){
			if (!isAlreadyTested(userProgress,newProgress)) {
				isAllowedToUpdate = true;
			}
		}else {
			isAllowedToUpdate = true;
		}

		if (isAllowedToUpdate){
			String query = "CALL update_progress(\"" + email + "\",\"" + newProgress + ",\");";
			try {
				dbConnection = dbConnectionInstance.getConnection();
				statement = dbConnection.prepareStatement(query);
				statement.executeQuery();
			} catch (SQLException e) {
				logger.error("Error in updating the user progress for user: " + email, e);
			} finally {
				dbConnectionInstance.closeConnection();
			}
		}
	}

	@Override
	public String getUserClass(String email) {
		String userClass = "";
		String query = "call CSCI5308_7_TEST.get_user_class('"+email+"');";
		try {
			dbConnection = dbConnectionInstance.getConnection();
			statement = dbConnection.prepareStatement(query);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				userClass = resultSet.getString("class");
			}
		} catch (SQLException e) {
			logger.error("Error in fetching the user class.", e);
		} finally {
			dbConnectionInstance.closeConnection();
		}
		return userClass;
	}

	private boolean isAlreadyTested(String[] userProgress, String newProgress){
		if (userProgress != null && newProgress!= null ){
			for (String i:userProgress) {
				if (i.equals(newProgress)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean isRegistered(String query) throws SQLException {
		boolean isRegistered = false;
		
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
			logger.error("Error in fetching the user registration details for user: " + email, e);
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return userName;
	}

	@Override
	public User getUser(String email) {
		User user = new User();
		int roleNum;
		String role;

		String query = "CALL get_user(\"" + email + "\");";
		try {
			dbConnection = dbConnectionInstance.getConnection();
			statement = dbConnection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				user.setEmail(resultSet.getString(1));
				user.setName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				roleNum = resultSet.getInt(4);
				if (roleNum == 1) {
					role = "Student";
				}
				else {
					role = "Instructor";
				}
				user.setRole(role);
			}
		} catch (SQLException e) {
			logger.error("Error in generating user object for user : " + email, e);
		} finally {
			dbConnectionInstance.closeConnection();
		}
		return user;
	}
}

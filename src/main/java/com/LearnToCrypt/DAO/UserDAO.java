package com.LearnToCrypt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DatabaseConnection.DBConnection;

public class UserDAO implements IUserDAO {

	DBConnection dbConnectionInstance = null;
	Connection dbConnection = null;
	private PreparedStatement statement;
	ResultSet resultSet;
	
	public UserDAO() {
		dbConnectionInstance = DBConnection.instance();
	}
	
	@Override
	public void createUser(User user) {
		
		int role = ( user.getRole().equals("Student") ) ? 1 : 2;
				
		String query = "CALL create_user(\""+user.getEmail()+"\", \""+ user.getName() + "\", \"" + user.getPassword() + "\", "+ role + ");";
		try {
			dbConnection = dbConnectionInstance.getConnection();
			
			statement = dbConnection.prepareStatement(query);		
			resultSet = statement.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Error in creating a new user.");
			System.out.println("Error : " + e.getMessage()); 
		} finally {
			dbConnectionInstance.closeConnection();
		}
	}
	
	@Override
	public boolean isUserValid(User user) {
		boolean isValid = false;
		
		String query = "CALL count_user(\""+ user.getEmail() + "\",\"" + user.getPassword() + "\");";
		
		try {
			dbConnection = dbConnectionInstance.getConnection();
			
			statement = dbConnection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			int userCount = 0;
			while (resultSet.next()) {
				userCount = resultSet.getInt(1);
			}
			
			if (userCount != 0) {
				isValid = true;
			}
		} catch (SQLException e) {
			System.out.println("Error in fetching the user credentials");
			System.out.println("Error : " + e.getMessage()); 
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
		} catch (SQLException e) {
			System.out.println("Error in fetching the user registration details");
			System.out.println("Error : " + e.getMessage()); 
		} finally {
			dbConnectionInstance.closeConnection();
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
		} finally {
			dbConnectionInstance.closeConnection();
		}	
		
		return userName;
	}
}

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
	private PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public UserDAO() {
		dbConnectionInstance = DBConnection.instance();
		dbConnection = dbConnectionInstance.getConnection();
	}
	
	@Override
	public void createUser(User user) {
		
		int role = ( user.getRole().equals("Student") ) ? 1 : 2;
				
		String query = "CALL create_user(\""+user.getEmail()+"\", \""+ user.getName() + "\", \"" + user.getPassword() + "\", "+ role + ");";
		try {
			preparedStatement = dbConnection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Error in creating a new user.");
			System.out.println("Error : " + e.getMessage()); 
		} finally {
			dbConnectionInstance.closeConnection();
		}
	}
}

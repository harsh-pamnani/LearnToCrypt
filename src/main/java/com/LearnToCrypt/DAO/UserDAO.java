package com.LearnToCrypt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DatabaseConnection.DBConnection;

public class UserDAO implements IUserDAO {

	Connection dbConnection = null;
	private PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public UserDAO() {
		dbConnection = DBConnection.instance().getConnection();
	}
	
	@Override
	public void createUser(User user) {
		String query = "CALL create_user(\""+user.getEmail()+"\", \""+ user.getName() + "\", \"" + user.getPassword() + "\", 1);";
		try {
			preparedStatement = dbConnection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

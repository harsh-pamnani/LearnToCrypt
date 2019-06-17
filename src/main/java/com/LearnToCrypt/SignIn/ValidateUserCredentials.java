package com.LearnToCrypt.SignIn;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

public class ValidateUserCredentials {
	
	DAOAbstractFactory abstractFactory;
	
	public ValidateUserCredentials() {
		abstractFactory = new DAOAbstractFactory();
	}
	
	public boolean validateCredentials(User user) {
					
		IUserDAO userDAO = abstractFactory.createUserDAO();
		
		return userDAO.isUserValid(user);
	}
	
}

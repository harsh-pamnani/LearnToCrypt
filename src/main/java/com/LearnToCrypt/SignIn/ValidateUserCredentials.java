package com.LearnToCrypt.SignIn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

public class ValidateUserCredentials {
	
	DAOAbstractFactory abstractFactory;
    private static final Logger logger = LogManager.getLogger(ValidateUserCredentials.class);

	public ValidateUserCredentials() {
		abstractFactory = new DAOAbstractFactory();
	}
	
	public boolean validateCredentials(User user) {
		IUserDAO userDAO = abstractFactory.createUserDAO();
		logger.info("Checking whether " + user.getEmail() + " is valid");
		
		return userDAO.isUserValid(user);
	}	
}
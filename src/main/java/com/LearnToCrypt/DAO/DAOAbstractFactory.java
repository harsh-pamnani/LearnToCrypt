package com.LearnToCrypt.DAO;

public class DAOAbstractFactory implements IDAOAbstractFactory {
	
	public IUserDAO createUserDAO() {
		return new UserDAO();
	}
	
}

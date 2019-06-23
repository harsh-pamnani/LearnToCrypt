package com.LearnToCrypt.DAO;

public class DAOAbstractFactory implements IDAOAbstractFactory {
	
	public IUserDAO createUserDAO() {
		return new UserDAO();
	}

	@Override
	public IPasswordUpdaterDAO createPasswordSetterDAO() {
		return new ProfileUpdateDAO();
	}

	@Override
	public INameSetterDAO createNameSetterDAO() {
		return new ProfileUpdateDAO();
	}
}

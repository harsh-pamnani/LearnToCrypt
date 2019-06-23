package com.LearnToCrypt.DAO;

public interface IDAOAbstractFactory {
	public IUserDAO createUserDAO();
	public IPasswordUpdaterDAO createPasswordSetterDAO();
	public INameSetterDAO createNameSetterDAO();
}

package com.LearnToCrypt.DAO;

public interface IDAOAbstractFactory {
	public IUserDAO createUserDAO();
	public IPasswordSetterDAO createPasswordSetterDAO();
	public INameSetterDAO createNameSetterDAO();
}

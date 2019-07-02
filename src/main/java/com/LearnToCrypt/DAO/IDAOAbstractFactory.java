package com.LearnToCrypt.DAO;

public interface IDAOAbstractFactory {
	public IUserDAO createUserDAO();
	public IAlgorithmDAO createAlgorithmDAO();
	public IPasswordUpdaterDAO createPasswordSetterDAO();
	public INameSetterDAO createNameSetterDAO();
}

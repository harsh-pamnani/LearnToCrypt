package com.LearnToCrypt.DAO;

public class DAOAbstractFactoryMock implements IDAOAbstractFactory {

	IUserDAO userDAO;
	IAlgorithmDAO algorithmDAO;
	IPasswordUpdaterDAO passwordUpdaterDAO;
	INameSetterDAO nameSetterDAO;

	public DAOAbstractFactoryMock() {
		userDAO = new UserDAOFactoryMock();
		algorithmDAO = new AlgorithmDAOFactoryMock();
		passwordUpdaterDAO = new ProfileUpdateDAOMock(userDAO);
		nameSetterDAO = new ProfileUpdateDAOMock(userDAO);
	}

	@Override
	public IUserDAO createUserDAO() {
		return userDAO;
	}

	@Override
	public IAlgorithmDAO createAlgorithmDAO() {
		return algorithmDAO;
	}

	@Override
	public IPasswordUpdaterDAO createPasswordSetterDAO() {
		return passwordUpdaterDAO;
	}

	@Override
	public INameSetterDAO createNameSetterDAO() {
		return nameSetterDAO;
	}

	@Override
	public IClassDAO createClassDAO() {
		return null;
	}

	@Override
	public IValidationRulesDAO createSignUpValidationRulesDAO() {
		return null;
	}

	@Override
	public IValidationRulesDAO createProfileNameUpdateValidationRulesDAO() {
		return null;
	}

	@Override
	public IValidationRulesDAO createProfilePasswordUpdateValidationRulesDAO() {
		return null;
	}
}

package com.LearnToCrypt.DAO;

public class DAOAbstractFactoryMock implements IDAOAbstractFactory {
	@Override
	public IUserDAO createUserDAO() {
		return new UserDAOFactoryMock();
	}

	@Override
	public IAlgorithmDAO createAlgorithmDAO() {
		return new AlgorithmDAOFactoryMock();
	}

	@Override
	public IPasswordUpdaterDAO createPasswordSetterDAO() {
		return null;
	}

	@Override
	public INameSetterDAO createNameSetterDAO() {
		return null;
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

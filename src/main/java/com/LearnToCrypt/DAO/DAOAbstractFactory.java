package com.LearnToCrypt.DAO;

public class DAOAbstractFactory implements IDAOAbstractFactory {
	
	@Override
	public IUserDAO createUserDAO() {
		return new UserDAO();
	}

	@Override
	public IAlgorithmDAO createAlgorithmDAO() {
		return new AlgorithmDAO();
	}

	@Override
	public IPasswordUpdaterDAO createPasswordSetterDAO() {
		return new ProfileUpdateDAO();
	}

	@Override
	public INameSetterDAO createNameSetterDAO() {
		return new ProfileUpdateDAO();
	}

	@Override
	public IClassDAO createClassDAO() {
		return new ClassDAO();
	}
	
	@Override
	public IValidationRulesDAO createSignUpValidationRulesDAO() {
		return new SignUpValidationRulesDAO();
	}

	@Override
	public IValidationRulesDAO createProfileNameUpdateValidationRulesDAO() {
		return new ProfileNameUpdateValidationRulesDAO();
	}

	@Override
	public IValidationRulesDAO createProfilePasswordUpdateValidationRulesDAO() {
		return new ProfilePasswordUpdateValidationRulesDAO();
	}
}

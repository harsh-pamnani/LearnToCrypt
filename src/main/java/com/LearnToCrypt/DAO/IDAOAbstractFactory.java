package com.LearnToCrypt.DAO;

public interface IDAOAbstractFactory {
	public IUserDAO createUserDAO();
	public IAlgorithmDAO createAlgorithmDAO();
	public IPasswordUpdaterDAO createPasswordSetterDAO();
	public INameSetterDAO createNameSetterDAO();
	public IClassDAO createClassDAO();
	public IValidationRulesDAO createSignUpValidationRulesDAO();
	public IValidationRulesDAO createProfileNameUpdateValidationRulesDAO();
	public IValidationRulesDAO createProfilePasswordUpdateValidationRulesDAO();
}

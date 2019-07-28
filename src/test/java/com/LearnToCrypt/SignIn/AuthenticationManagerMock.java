package com.LearnToCrypt.SignIn;

import com.LearnToCrypt.DAO.DAOAbstractFactoryMock;
import com.LearnToCrypt.DAO.IUserDAO;

import java.util.ArrayList;

public class AuthenticationManagerMock {
	private static AuthenticationManagerMock authenticationManagerInstance = null;
	private static ArrayList<String> authenticatedUsers;
	DAOAbstractFactoryMock daoAbstractFactory;

	public static AuthenticationManagerMock instance() {
		if (null == authenticationManagerInstance) {
			authenticationManagerInstance = new AuthenticationManagerMock();
		}
		return authenticationManagerInstance;
	}

	private AuthenticationManagerMock() {
		daoAbstractFactory = new DAOAbstractFactoryMock();
		authenticatedUsers = new ArrayList<String>();
	}

	public void addAuthenticatedUser(String email) {
		authenticatedUsers.add(email);
	}

	public void removeAuthenticatedUser(String email) {
		authenticatedUsers.remove(email);
	}

	public boolean isUserAuthenticated(String email) {

		return authenticatedUsers.contains(email);
	}

	public String getUsername(String email) {
		IUserDAO userDAOName = daoAbstractFactory.createUserDAO();
		String userName = userDAOName.getUserName(email);
		return userName;
	}
}

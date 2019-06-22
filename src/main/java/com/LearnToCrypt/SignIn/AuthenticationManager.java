package com.LearnToCrypt.SignIn;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

public class AuthenticationManager {
	
	private static AuthenticationManager authenticationManagerInstance = null;
	private static Map<String, String> authenticatedUsers;
	DAOAbstractFactory daoAbstractFactory;
	
	public static AuthenticationManager instance() {
		if (null == authenticationManagerInstance) {
			authenticationManagerInstance = new AuthenticationManager();
		}
		return authenticationManagerInstance;
	}
	
	private AuthenticationManager() {
		daoAbstractFactory = new DAOAbstractFactory();
		authenticatedUsers = new HashMap<String, String>();
	}
	
	public void addAuthenticatedUser(HttpSession httpSession, String email) {
		authenticatedUsers.put(httpSession.getId(), email);
	}
	
	public void removeAuthenticatedUser(HttpSession httpSession) {
		authenticatedUsers.remove(httpSession.getId());
	}
	
	public boolean isUserAuthenticated(HttpSession httpSession) {
		return authenticatedUsers.containsKey(httpSession.getId());
	}
	
	public String getUsername(HttpSession httpSession) {
		IUserDAO userDAOName = daoAbstractFactory.createUserDAO();
		String userName = userDAOName.getUserName(authenticatedUsers.get(httpSession.getId()));
		
		return userName;
	}
}
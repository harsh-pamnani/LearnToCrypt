package com.LearnToCrypt.SignIn;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

public class AuthenticationManager {
	
	private static AuthenticationManager authenticationManagerInstance = null;
	private static Map<String, String> authenticatedUsers;
	DAOAbstractFactory daoAbstractFactory;
    private static final Logger logger = LogManager.getLogger(AuthenticationManager.class);
	
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
		
		logger.info(email + " added to authenticated users list");
	}
	
	public void removeAuthenticatedUser(HttpSession httpSession) {
		authenticatedUsers.remove(httpSession.getId());
		
		logger.info(getEmail(httpSession) + " removed from authenticated users list");
	}
	
	public boolean isUserAuthenticated(HttpSession httpSession) {
		logger.info("Checking whether session " + httpSession.getId() + " is authenticated");

		return authenticatedUsers.containsKey(httpSession.getId());
	}
	
	public String getUsername(HttpSession httpSession) {
		IUserDAO userDAOName = daoAbstractFactory.createUserDAO();
		String userName = userDAOName.getUserName(authenticatedUsers.get(httpSession.getId()));
		logger.info("Getting username from http session for user: " + userName);
		
		return userName;
	}

	public String getEmail(HttpSession httpSession) {
		String email = authenticatedUsers.get(httpSession.getId());
		
		logger.info("Email accessed from http session for user " + email);
		return email;
	}
}
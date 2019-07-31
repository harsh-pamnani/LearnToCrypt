package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ProfileCreator implements IProfileCreator{
	private static final Logger logger = LogManager.getLogger(ProfileCreator.class);
	private static ProfileCreator instance;

	public static ProfileCreator getInstance() {
		if (null == instance) {
			instance = new ProfileCreator();
		}
		return instance;
	}

	private ProfileCreator() {
		logger.info("Class instantiated");
	}

	@Override
	public IUserProfileBridge getProfile(AuthenticationManager authenticationManager,
										 HttpSession httpSession,
										 IDAOAbstractFactory abstractFactory) throws AuthenticationException, SQLException {
		IUserProfileBridge profile;
		logger.info("Authenticating user");
		if (authenticationManager.isUserAuthenticated(httpSession)) {
			logger.info("User authenticated successfully");
			String email = authenticationManager.getEmail(httpSession);
			profile = new UserProfile(email, abstractFactory);
		} else {
			String error = "User authentication failed";
			logger.error(error);
			throw new AuthenticationException(error);
		}
		return profile;
	}
}

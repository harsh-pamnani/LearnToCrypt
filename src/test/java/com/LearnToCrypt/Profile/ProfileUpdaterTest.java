package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.DAOAbstractFactoryMock;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.HashingAlgorithm.IHash;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProfileUpdaterTest {

	private static final Logger logger = LogManager.getLogger(ProfileUpdaterTest.class);
	private BusinessModelAbstractFactory businessModelAbstractFactory;
	private IDAOAbstractFactory abstractFactory;
	private IUserDAO userDAO;
	private User user;
	private IHash md5;
	private IUserNameChanger userNameChanger;
	private IPasswordChanger passwordChanger;
	private IUserProfileBridge profileBridge;
	private IUpdateProfile updateProfile;
	private IProfileValidator validator;

	public ProfileUpdaterTest() {
		logger.info("Starting tests");
		logger.info("Start - test setup");
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
		abstractFactory = new DAOAbstractFactoryMock();
		userDAO = abstractFactory.createUserDAO();
		user = businessModelAbstractFactory.createUser();
		user.setEmail("update@profile.com");
		user.setPassword("ProfileUpdate@123");
		user.setName("Profile Update");
		user.setRole("Student");
		md5 = new MD5();
		if(!userDAO.isUserRegistered(user.getEmail())) {
			userDAO.createUser(user);
		}
		try {
			profileBridge = new UserProfile(user.getEmail(), abstractFactory);
		} catch (SQLException e) {
			logger.error("Test should not connect to database. Exception: " + e.getMessage());
		}
		validator = new ProfileValidator(profileBridge, md5);
		userNameChanger = new UserNameChanger(abstractFactory);
		passwordChanger = new PasswordChanger(abstractFactory);
		updateProfile = new ProfileUpdater(validator, userNameChanger, passwordChanger);
		logger.info("End - test setup");
	}

	@Test
	public void testUpdate() {
		try {
			updateProfile.update(profileBridge, "Profile",
					"NewPass@1234",
					"NewPass@1234");

			User user = userDAO.getUser(profileBridge.getEmail());
		} catch (ValidationException e) {
			logger.error("Validation Error in Test: " + e.getMessage());
		} catch (SQLException e) {
			logger.error("Test should not connect to database. Exception: " + e.getMessage());
		}
		assertEquals("Profile", user.getName());
		assertEquals(md5.generateHashValue("NewPass@1234"), user.getPassword());
	}
}

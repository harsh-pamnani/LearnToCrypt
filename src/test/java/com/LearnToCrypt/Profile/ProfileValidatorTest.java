package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ProfileValidatorTest {
	private static final Logger logger = LogManager.getLogger(ProfileValidatorTest.class);

	private IProfileValidator validator;
	private BusinessModelAbstractFactory businessModelAbstractFactory;
	private IDAOAbstractFactory abstractFactory;
	private IUserDAO userDAO;
	private User user;
	private IHash md5;
	private IUserProfileBridge profileBridge;

	public ProfileValidatorTest() {
		logger.info("Starting tests");
		logger.info("Start - test setup");
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
		abstractFactory = new DAOAbstractFactoryMock();
		userDAO = abstractFactory.createUserDAO();
		user = businessModelAbstractFactory.createUser();
		user.setEmail("validate@profile.com");
		user.setPassword("ProfileValidatee@123");
		user.setName("Profile Validate");
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
		logger.info("End - test setup");
	}

	@Test
	public void testValidateName() {
		try {
			validator.validateName("ABC1");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof ValidationException);
		}
		try {
			validator.validateName("ABC@$");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof ValidationException);
		}
	}

	@Test
	public void testValidatePassword() {
		try {
			validator.validatePassword("Abc1234567", "Abc1234567");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof ValidationException);
		}
		try {
			validator.validatePassword("Abcd@123456", "Abcdef@123456");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof ValidationException);
		}
		try {
			validator.validatePassword("Abcd@1", "Abcd@1");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof ValidationException);
		}
		try {
			validator.validatePassword("ABCDE@1234567", "ABCDE@1234567");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof ValidationException);
		}
		try {
			validator.validatePassword("abcde@1234567", "abcde@1234567");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof ValidationException);
		}
	}
}

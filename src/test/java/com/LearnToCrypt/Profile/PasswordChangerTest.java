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

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PasswordChangerTest {
	private static final Logger logger = LogManager.getLogger(PasswordChangerTest.class);
	private BusinessModelAbstractFactory businessModelAbstractFactory;
	private IDAOAbstractFactory abstractFactory;
	private IUserDAO userDAO;
	private User user;
	private IHash md5;
	private IPasswordChanger passwordChanger;

	public PasswordChangerTest() {
		logger.info("Starting tests");
		logger.info("Start - test setup");
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
		abstractFactory = new DAOAbstractFactoryMock();
		userDAO = abstractFactory.createUserDAO();
		user = businessModelAbstractFactory.createUser();
		user.setEmail("update@pass.com");
		user.setPassword("PasswordUpdate@123");
		user.setName("Pass Update");
		user.setRole("Student");
		md5 = new MD5();
		if(!userDAO.isUserRegistered(user.getEmail())) {
			userDAO.createUser(user);
		}
		passwordChanger = new PasswordChanger(abstractFactory);
		logger.info("End - test setup");
	}

	@Test
	public void testChangePassword() {
		try {
			passwordChanger.changePassword(user.getEmail(), "PassUpdate@123");
			User updatedUser = userDAO.getUser(user.getEmail());
			String hashedPassword = md5.generateHashValue("PassUpdate@123");
			assertEquals(hashedPassword, updatedUser.getPassword());

		} catch (SQLException e) {
			logger.error("Test should not connect to database. Exception: " + e.getMessage());
			fail();
		}
	}
}

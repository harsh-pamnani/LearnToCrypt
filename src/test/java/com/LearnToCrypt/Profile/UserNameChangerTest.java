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

import static org.junit.Assert.*;

public class UserNameChangerTest {
	private static final Logger logger = LogManager.getLogger(UserNameChangerTest.class);
	private BusinessModelAbstractFactory businessModelAbstractFactory;
	private IDAOAbstractFactory abstractFactory;
	private IUserDAO userDAO;
	private User user;
	private IHash md5;
	private IUserNameChanger nameChanger;

	public UserNameChangerTest() {
		logger.info("Starting tests");
		logger.info("Start - test setup");
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
		abstractFactory = new DAOAbstractFactoryMock();
		userDAO = abstractFactory.createUserDAO();
		user = businessModelAbstractFactory.createUser();
		user.setEmail("update@name.com");
		user.setPassword("NameUpdate@123");
		user.setName("Name Update");
		user.setRole("Student");
		md5 = new MD5();
		if(!userDAO.isUserRegistered(user.getEmail())) {
			userDAO.createUser(user);
		}
		nameChanger = new UserNameChanger(abstractFactory);
		logger.info("End - test setup");
	}

	@Test
	public void testChangeName() {
		try {
			nameChanger.changeName(user.getEmail(), "New Name");
			User updatedUser = userDAO.getUser(user.getEmail());
			assertEquals("New Name", updatedUser.getName());
			assertNotEquals("Name Update", updatedUser.getName());
		} catch (SQLException e) {
			logger.error("Test should not connect to database. Exception: " + e.getMessage());
			fail();
		}
	}

}

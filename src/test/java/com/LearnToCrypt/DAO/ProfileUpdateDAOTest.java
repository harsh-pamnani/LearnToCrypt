package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProfileUpdateDAOTest {
	BusinessModelAbstractFactory modelAbstractFactory;
	IDAOAbstractFactory abstractFactory;
	INameSetterDAO nameSetterDAO;
	IPasswordUpdaterDAO passwordUpdaterDAO;
	IUserDAO userDAO;
	User user;
	MD5 md5;

	public ProfileUpdateDAOTest() {
		abstractFactory = new DAOAbstractFactory();
		userDAO = abstractFactory.createUserDAO();
		md5 = new MD5();
		nameSetterDAO = abstractFactory.createNameSetterDAO();
		passwordUpdaterDAO = abstractFactory.createPasswordSetterDAO();
		modelAbstractFactory = new BusinessModelAbstractFactory();
		user = modelAbstractFactory.createUser();
		user.setEmail("test@testing.com");
		user.setPassword("Test@123");
		user.setName("Test");
		user.setRole("Student");
		if(!userDAO.isUserRegistered(user)) {
			userDAO.createUser(user);
		}
	}

	@Test
	public void testSetPassword() {
		passwordUpdaterDAO.setPassword(user.getEmail(), "Test@1234");
		String hash = md5.generateHashValue("Test@1234");
		User testUser = userDAO.getUser(user.getEmail());
		passwordUpdaterDAO.setPassword(user.getEmail(), user.getPassword());
		assertEquals(testUser.getPassword(), hash);
	}

	@Test
	public void testSetName() {
		nameSetterDAO.setName(user.getEmail(), "Test Name");
		User testUser = userDAO.getUser(user.getEmail());
		nameSetterDAO.setName(user.getEmail(), user.getName());
		assertEquals("Test Name", testUser.getName());
	}

	@Test
	public void testSetResetToken() {
		passwordUpdaterDAO.setResetToken(user.getEmail(), "test-token");
		String email = passwordUpdaterDAO.getEmailFromToken("test-token");
		assertEquals(user.getEmail(), email);
	}

	@Test
	public void testGetEmailFromToken() {
		passwordUpdaterDAO.setResetToken(user.getEmail(), "test-token");
		String email = passwordUpdaterDAO.getEmailFromToken("test-token");
		assertEquals(user.getEmail(), email);
	}
}

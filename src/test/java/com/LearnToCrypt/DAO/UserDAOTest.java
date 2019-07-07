package com.LearnToCrypt.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import org.junit.runner.notification.RunListener;

public class UserDAOTest {

	UserDAOFactoryMock userDAOFactoryMock;
	BusinessModelAbstractFactory businessModelAbstractFactory;
	User user;
	IUserDAO userDAO;
	
	public UserDAOTest() {
		userDAOFactoryMock = new UserDAOFactoryMock();
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
		user = businessModelAbstractFactory.createUser();
		userDAO = new UserDAO();
	}
	
	@Test
	public void testCreateUser() {
		user.setEmail("malcolmvaughn@gmail.com");
		user.setName("Malcolm Vaughn");
		user.setPassword("Vaughn!8z5");
		user.setRole("Instructor");
		
		assertFalse(userDAOFactoryMock.isUserValid(user));
		
		userDAOFactoryMock.createUser(user);
		assertTrue(userDAOFactoryMock.isUserValid(user));
	}
	
	@Test
	public void testIsUserValid() {
		user.setEmail("milly@gmail.com");
		user.setPassword("Milly@9876");
		assertTrue(userDAOFactoryMock.isUserValid(user));
		
		user.setEmail("millan@fake.com");
		user.setPassword("millan@7648");
		assertFalse(userDAOFactoryMock.isUserValid(user));
	}
	
	@Test
	public void testIsUserRegistered() {
		user.setEmail("rob@gmail.com");
		assertTrue(userDAOFactoryMock.isUserRegistered(user));
		
		user.setEmail("xyz@hotmail.com");
		assertFalse(userDAOFactoryMock.isUserRegistered(user));
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("Shelley Dhillan", userDAOFactoryMock.getUserName("shelley@gmail.com"));
		
		assertNotEquals("Harsh Pamnani", userDAOFactoryMock.getUserName("shelley@gmail.com"));
	}

	@Test
	public void testGetUser() {
		assertTrue(userDAOFactoryMock.getUser("rob@gmail.com") instanceof User);
	}
	
	@Test
	public void testGetRole() {
		assertEquals("Instructor", userDAOFactoryMock.getUserRole("rob@gmail.com"));
		
		assertNotEquals("Student", userDAOFactoryMock.getUserRole("rob@gmail.com"));
	}

	@Test
	public void testGetProgress(){
		String progressFromDatabase[] = userDAO.getProgress("test@myProgress.com");
		String expect[] = {"Caesar Cipher","Rail Fence Cipher"};
		assertEquals(progressFromDatabase.length,expect.length);
		for(int i=0; i<progressFromDatabase.length; i++){
			assertEquals(progressFromDatabase[i],expect[i]);
		}
	}

	@Test
	public void testUpdateProgress_ProgressAlreadyExist(){
		userDAO.updateProgress("test@myProgress.com","Caesar Cipher");
		String progressFromDatabase[] = userDAO.getProgress("test@myProgress.com");
		String expect[] = {"Caesar Cipher","Rail Fence Cipher"};
		assertEquals(progressFromDatabase.length,expect.length);
		for(int i=0; i<progressFromDatabase.length; i++){
			assertEquals(progressFromDatabase[i],expect[i]);
		}
	}
}

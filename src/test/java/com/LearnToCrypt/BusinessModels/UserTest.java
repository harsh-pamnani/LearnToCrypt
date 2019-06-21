package com.LearnToCrypt.BusinessModels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class UserTest {
	
	User user;
	BusinessModelAbstractFactory businessModelAbstractFactory;
	
	public UserTest() {
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
		user = businessModelAbstractFactory.createUser();
	}
	
	
	@Test
	public void testGetEmail() {
		user.setEmail("harsh@gmail.com");
		assertEquals("harsh@gmail.com", user.getEmail());
		
		assertNotEquals("harsh@hotmail.ca", user.getEmail());
	}
	
	@Test
	public void testGetName() {
		user.setName("Harsh Pamnani");
		assertEquals("Harsh Pamnani", user.getName());
		
		assertNotEquals("Viraj", user.getName());
	}
	

	@Test
	public void testGetPassword() {
		user.setPassword("Harsh@123");
		assertEquals("Harsh@123", user.getPassword());
		
		assertNotEquals("Harsh!714", user.getPassword());
	}
	

	@Test
	public void testGetRole() {
		user.setRole("Instructor");
		assertEquals("Instructor", user.getRole());
		
		assertNotEquals("Student", user.getRole());
	}
	
	@Test
	public void testSetEmail() {
		user.setEmail("tony1993@apple.ca");
		assertEquals("tony1993@apple.ca", user.getEmail());
		
		assertNotEquals("tony121@apple.ca", user.getEmail());
	}
	
	@Test
	public void testSetName() {
		user.setName("Tony");
		assertEquals("Tony", user.getName());
		
		assertNotEquals("Deep", user.getName());
	}
	

	@Test
	public void testSetPassword() {
		user.setPassword("Tony@123");
		assertEquals("Tony@123", user.getPassword());
		
		assertNotEquals("Revan@734", user.getPassword());
	}
	

	@Test
	public void testSetRole() {
		user.setRole("Student");
		assertEquals("Student", user.getRole());
		
		assertNotEquals("Instructor", user.getRole());
	}
}

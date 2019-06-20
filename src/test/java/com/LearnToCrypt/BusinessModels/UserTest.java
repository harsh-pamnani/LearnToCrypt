package com.LearnToCrypt.BusinessModels;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
	
	User user;
	
	@Test
	public void testGetEmail() {
		user = new User();
		user.setEmail("harsh@gmail.com");
		assertEquals("harsh@gmail.com", user.getEmail());
	}
	
	@Test
	public void testGetName() {
		user = new User();
		user.setName("Harsh Pamnani");
		assertEquals("Harsh Pamnani", user.getName());
	}
	

	@Test
	public void testGetPassword() {
		user = new User();
		user.setPassword("Harsh@123");
		assertEquals("Harsh@123", user.getPassword());
	}
	

	@Test
	public void testGetRole() {
		user = new User();
		user.setRole("Instructor");
		assertEquals("Instructor", user.getRole());
	}
	
	@Test
	public void testSetEmail() {
		user = new User();
		user.setEmail("tony1993@apple.ca");
		assertEquals("tony1993@apple.ca", user.getEmail());
	}
	
	@Test
	public void testSetName() {
		user = new User();
		user.setName("Tony");
		assertEquals("Tony", user.getName());
	}
	

	@Test
	public void testSetPassword() {
		user = new User();
		user.setPassword("Tony@123");
		assertEquals("Tony@123", user.getPassword());
	}
	

	@Test
	public void testSetRole() {
		user = new User();
		user.setRole("Student");
		assertEquals("Student", user.getRole());
	}
}

package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordLengthValidationTest {

	User user;
	IValidation passwordLengthValidation;
	
	public PasswordLengthValidationTest() {
		passwordLengthValidation = new PasswordLengthValidation();
		
		user = new User();
		user.setEmail("Shiva@gmail.com");
		user.setName("Shiva Armel");
		user.setPassword("Armel@87633");
		user.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(passwordLengthValidation.isValid(user, "Armel@87633"));
		
		user.setPassword("Armel@4");
		assertFalse(passwordLengthValidation.isValid(user, "Armel@4"));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Password must be at least 8 characters long.", passwordLengthValidation.getError());
		
		assertNotEquals("XYZ error has occured.", passwordLengthValidation.getError());
	}
}

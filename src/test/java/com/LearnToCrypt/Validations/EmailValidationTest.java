package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class EmailValidationTest {

	User user;
	IValidation emailValidation;
	
	public EmailValidationTest() {
		emailValidation = new EmailValidation();
		
		user = new User();
		user.setEmail("Harsh@gmail.com");
		user.setName("Harsh Pamnani");
		user.setPassword("Hars@123");
		user.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(emailValidation.isValid(user, "Hars@123"));
		
		user.setEmail("harsh@gmail");
		assertFalse(emailValidation.isValid(user, "Hars@123"));
		
		user.setEmail("harsh@");
		assertFalse(emailValidation.isValid(user, "Hars@123"));
		
		user.setEmail("harshgmail.com");
		assertFalse(emailValidation.isValid(user, "Hars@123"));
		
		user.setEmail("harsh pam@gmail.com");
		assertFalse(emailValidation.isValid(user, "Hars@123"));
		
		user.setEmail("harsh@gmail.");
		assertFalse(emailValidation.isValid(user, "Hars@123"));	
	}
	
	@Test
	public void testGetError() {
		assertEquals("Email id is not valid.", emailValidation.getError());
		
		assertNotEquals("XYZ error has occured.", emailValidation.getError());
	}
}

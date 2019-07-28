package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class EmailValidationTest {

	IValidation emailValidation;
	IValidationParams params;
	
	public EmailValidationTest() {
		emailValidation = new EmailValidation();
		emailValidation.setValue("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
		params = new ValidationParams();
		params.setEmail("Harsh@gmail.com");
		params.setName("Harsh Pamnani");
		params.setPassword("Hars@123");
		params.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(emailValidation.isValid(params));
		
		params.setEmail("harsh@gmail");
		assertFalse(emailValidation.isValid(params));
		
		params.setEmail("harsh@");
		assertFalse(emailValidation.isValid(params));
		
		params.setEmail("harshgmail.com");
		assertFalse(emailValidation.isValid(params));
		
		params.setEmail("harsh pam@gmail.com");
		assertFalse(emailValidation.isValid(params));
		
		params.setEmail("harsh@gmail.");
		assertFalse(emailValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Email id is not valid.", emailValidation.getError());
		
		assertNotEquals("XYZ error has occured.", emailValidation.getError());
	}
}

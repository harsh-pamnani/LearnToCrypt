package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PasswordLengthValidationTest {

	IValidationParams params;
	IValidation passwordLengthValidation;
	
	public PasswordLengthValidationTest() {
		passwordLengthValidation = new PasswordLengthValidation();
		passwordLengthValidation.setValue("8");
		params = new ValidationParams();
		params.setEmail("Shiva@gmail.com");
		params.setName("Shiva Armel");
		params.setPassword("Armel@87633");
		params.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(passwordLengthValidation.isValid(params));
		
		params.setPassword("Armel@4");
		assertFalse(passwordLengthValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Password must be at least 8 characters long.", passwordLengthValidation.getError());
		
		assertNotEquals("XYZ error has occured.", passwordLengthValidation.getError());
	}
}

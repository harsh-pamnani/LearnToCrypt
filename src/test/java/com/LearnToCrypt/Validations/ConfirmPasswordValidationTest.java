package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class ConfirmPasswordValidationTest {

	IValidationParams params;
	IValidation confrimPasswordValidation;
	
	public ConfirmPasswordValidationTest() {
		confrimPasswordValidation = new ConfirmPasswordValidation();

		params = new ValidationParams();
		params.setEmail("Aman@gmail.com");
		params.setName("Aman Arya");
		params.setPassword("Aman!12345");
		params.setRole("Student");
	}
	
	@Test
	public void testIsValid() {
		params.setConfirmPassword(params.getPassword());
		assertTrue(confrimPasswordValidation.isValid(params));
		params.setConfirmPassword("abc");
		assertFalse(confrimPasswordValidation.isValid(params));
		// TODO: Validation tests are missing
		assertFalse(confrimPasswordValidation.isValid(params));
		
		assertFalse(confrimPasswordValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Confirm Password doesn't match.", confrimPasswordValidation.getError());
		
		assertNotEquals("XYZ error has occured.", confrimPasswordValidation.getError());
	}
}

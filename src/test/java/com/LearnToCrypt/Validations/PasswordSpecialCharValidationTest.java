package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordSpecialCharValidationTest {

	IValidation passwordSpecialCharValidation;
	IValidationParams params;
	
	public PasswordSpecialCharValidationTest() {
		passwordSpecialCharValidation = new PasswordSpecialCharValidation();
		passwordSpecialCharValidation.setValue(".*[!@#$%^&*()].*");
		params = new ValidationParams();
		params.setEmail("Maryiaanda1@apple.org");
		params.setName("Maryia Anda");
		params.setPassword("Anda@11111");
		params.setRole("Student");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(passwordSpecialCharValidation.isValid(params));
		
		params.setPassword("Anda11111");
		assertFalse(passwordSpecialCharValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Password must contain at least 1 special charachter.", passwordSpecialCharValidation.getError());
		
		assertNotEquals("XYZ error has occured.", passwordSpecialCharValidation.getError());
	}
}

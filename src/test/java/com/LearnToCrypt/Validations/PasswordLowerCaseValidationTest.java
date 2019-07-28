package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PasswordLowerCaseValidationTest {

	IValidationParams params;
	IValidation passwordLowerCaseValidation;
	
	public PasswordLowerCaseValidationTest() {
		passwordLowerCaseValidation = new PasswordLowerCaseValidation();
		passwordLowerCaseValidation.setValue(".*[a-z].*");
		params = new ValidationParams();
		params.setEmail("Balbinoeleonora@abc.org");
		params.setName("Balbino Eleonora");
		params.setPassword("Balbino@76543");
		params.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(passwordLowerCaseValidation.isValid(params));
		
		params.setPassword("BALBINO@76543");
		assertFalse(passwordLowerCaseValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Password must contain at least 1 lowercase letter.", passwordLowerCaseValidation.getError());
		
		assertNotEquals("XYZ error has occured.", passwordLowerCaseValidation.getError());
	}
}

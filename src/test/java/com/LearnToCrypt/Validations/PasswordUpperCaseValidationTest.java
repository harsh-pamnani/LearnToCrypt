package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordUpperCaseValidationTest {

	IValidation passwordUpperCaseValidation;
	IValidationParams params;
	
	public PasswordUpperCaseValidationTest() {
		passwordUpperCaseValidation = new PasswordUpperCaseValidation();
		passwordUpperCaseValidation.setValue(".*[A-Z].*");
		params = new ValidationParams();
		params.setEmail("Araceli@abc.org");
		params.setName("Araceli Dorinel");
		params.setPassword("Dorinel@911");
		params.setRole("Student");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(passwordUpperCaseValidation.isValid(params));

		params.setPassword("dorinel@911");
		assertFalse(passwordUpperCaseValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Password must contain at least 1 uppercase letter.", passwordUpperCaseValidation.getError());
		
		assertNotEquals("XYZ error has occured.", passwordUpperCaseValidation.getError());
	}
}

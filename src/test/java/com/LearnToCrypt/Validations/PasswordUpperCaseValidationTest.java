package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordUpperCaseValidationTest {

	User user;
	IValidation passwordUpperCaseValidation;
	
	public PasswordUpperCaseValidationTest() {
		passwordUpperCaseValidation = new PasswordUpperCaseValidation();
		passwordUpperCaseValidation.setValue(".*[A-Z].*");
		user = new User();
		user.setEmail("Araceli@abc.org");
		user.setName("Araceli Dorinel");
		user.setPassword("Dorinel@911");
		user.setRole("Student");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(passwordUpperCaseValidation.isValid(user));
		
		user.setPassword("dorinel@911");
		assertFalse(passwordUpperCaseValidation.isValid(user));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Password must contain at least 1 uppercase letter.", passwordUpperCaseValidation.getError());
		
		assertNotEquals("XYZ error has occured.", passwordUpperCaseValidation.getError());
	}
}

package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordLowerCaseValidationTest {

	User user;
	IValidation passwordLowerCaseValidation;
	
	public PasswordLowerCaseValidationTest() {
		passwordLowerCaseValidation = new PasswordLowerCaseValidation();
		
		user = new User();
		user.setEmail("Balbinoeleonora@abc.org");
		user.setName("Balbino Eleonora");
		user.setPassword("Balbino@76543");
		user.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(passwordLowerCaseValidation.isValid(user, "Balbino@76543"));
		
		user.setPassword("BALBINO@76543");
		assertFalse(passwordLowerCaseValidation.isValid(user, "BALBINO@76543"));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Password must contain at least 1 lowercase letter.", passwordLowerCaseValidation.getError());
		
		assertNotEquals("XYZ error has occured.", passwordLowerCaseValidation.getError());
	}
}

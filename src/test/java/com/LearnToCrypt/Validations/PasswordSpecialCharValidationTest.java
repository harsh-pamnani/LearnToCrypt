package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class PasswordSpecialCharValidationTest {

	User user;
	IValidation passwordSpecialCharValidation;
	
	public PasswordSpecialCharValidationTest() {
		passwordSpecialCharValidation = new PasswordSpecialCharValidation();
		
		user = new User();
		user.setEmail("Maryiaanda1@apple.org");
		user.setName("Maryia Anda");
		user.setPassword("Anda@11111");
		user.setRole("Student");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(passwordSpecialCharValidation.isValid(user, "Anda@11111"));
		
		user.setPassword("Anda11111");
		assertFalse(passwordSpecialCharValidation.isValid(user, "Anda11111"));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Password must contain at least 1 special charachter.", passwordSpecialCharValidation.getError());
		
		assertNotEquals("XYZ error has occured.", passwordSpecialCharValidation.getError());
	}
}

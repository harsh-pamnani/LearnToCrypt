package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class ConfirmPasswordValidationTest {

	User user;
	IValidation confrimPasswordValidation;
	
	public ConfirmPasswordValidationTest() {
		confrimPasswordValidation = new ConfirmPasswordValidation();
		
		user = new User();
		user.setEmail("Aman@gmail.com");
		user.setName("Aman Arya");
		user.setPassword("Aman!123");
		user.setRole("Student");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(confrimPasswordValidation.isValid(user, "Aman!123"));
		
		assertFalse(confrimPasswordValidation.isValid(user, "Qwe#1612"));
		
		assertFalse(confrimPasswordValidation.isValid(user, ""));
		
		assertFalse(confrimPasswordValidation.isValid(user, null));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Confirm Password doesn't match.", confrimPasswordValidation.getError());
		
		assertNotEquals("XYZ error has occured.", confrimPasswordValidation.getError());
	}
}

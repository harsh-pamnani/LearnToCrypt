package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class NameEmptyValidationTest {

	User user;
	IValidation nameEmptyValidation;
	
	public NameEmptyValidationTest() {
		nameEmptyValidation = new NameEmptyValidation();
		
		user = new User();
		user.setEmail("Harsh@gmail.com");
		user.setName("Harsh Pamnani");
		user.setPassword("Hars@123");
		user.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {		
		assertTrue(nameEmptyValidation.isValid(user));
		
		user.setName("");
		assertFalse(nameEmptyValidation.isValid(user));
		
		user.setName(null);
		assertFalse(nameEmptyValidation.isValid(user));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Name can not be empty.", nameEmptyValidation.getError());
		
		assertNotEquals("XYZ error has occured.", nameEmptyValidation.getError());
	}
}

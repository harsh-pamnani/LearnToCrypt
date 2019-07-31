package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NameEmptyValidationTest {

	IValidation nameEmptyValidation;
	IValidationParams params;
	
	public NameEmptyValidationTest() {
		nameEmptyValidation = new NameEmptyValidation();
		
		params = new ValidationParams();
		params.setEmail("Harsh@gmail.com");
		params.setName("Harsh Pamnani");
		params.setPassword("Hars@123");
		params.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {		
		assertTrue(nameEmptyValidation.isValid(params));
		
		params.setName("");
		assertFalse(nameEmptyValidation.isValid(params));
		
		params.setName(null);
		assertFalse(nameEmptyValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Name can not be empty.", nameEmptyValidation.getError());
		
		assertNotEquals("XYZ error has occured.", nameEmptyValidation.getError());
	}
}

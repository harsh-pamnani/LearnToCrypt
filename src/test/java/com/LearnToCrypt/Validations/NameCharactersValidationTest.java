package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class NameCharactersValidationTest {
	IValidation nameCharactersValidation;
	IValidationParams params;
	
	public NameCharactersValidationTest() {
		nameCharactersValidation = new NameCharactersValidation();
		params = new ValidationParams();
		nameCharactersValidation.setValue("[a-zA-Z ]+");
		params = new ValidationParams();
		params.setEmail("linus@gmail.com");
		params.setName("Avgust Linus");
		params.setPassword("Linus@666");
		params.setRole("Student");
	}
	
	@Test
	public void testIsValid() {		
		assertTrue(nameCharactersValidation.isValid(params));

		params.setName("Avgust Linus 1");
		assertFalse(nameCharactersValidation.isValid(params));

		params.setName("Avgust Linus @");
		assertFalse(nameCharactersValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Name can not contain any digits or special characters.", nameCharactersValidation.getError());
		
		assertNotEquals("XYZ error has occured.", nameCharactersValidation.getError());
	}
}

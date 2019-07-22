package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class NameCharactersValidationTest {
	User user;
	IValidation nameCharactersValidation;
	
	public NameCharactersValidationTest() {
		nameCharactersValidation = new NameCharactersValidation();
		nameCharactersValidation.setValue("[a-zA-Z ]+");
		user = new User();
		user.setEmail("linus@gmail.com");
		user.setName("Avgust Linus");
		user.setPassword("Linus@666");
		user.setRole("Student");
	}
	
	@Test
	public void testIsValid() {		
		assertTrue(nameCharactersValidation.isValid(user, "Linus@666"));
		
		user.setName("Avgust Linus 1");
		assertFalse(nameCharactersValidation.isValid(user, "Linus@666"));
		
		user.setName("Avgust Linus @");
		assertFalse(nameCharactersValidation.isValid(user, "Linus@666"));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Name can not contain any digits or special characters.", nameCharactersValidation.getError());
		
		assertNotEquals("XYZ error has occured.", nameCharactersValidation.getError());
	}
}

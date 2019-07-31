package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class RoleValidationTest {

	IValidation roleValidation;
	IValidationParams params;
	
	public RoleValidationTest() {
		roleValidation = new RoleValidation();
		roleValidation.setValue("Student Instructor");
		params = new ValidationParams();
		params.setEmail("Orfeomarta@abc.org");
		params.setName("Orfeo Marta");
		params.setPassword("Marta@53452");
		params.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(roleValidation.isValid(params));
		
		params.setRole("Student");
		assertTrue(roleValidation.isValid(params));
		
		params.setRole("");
		assertFalse(roleValidation.isValid(params));
		
		params.setRole("XYZ");
		assertFalse(roleValidation.isValid(params));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Role can not be empty.", roleValidation.getError());
		
		assertNotEquals("XYZ error has occured.", roleValidation.getError());
	}
}

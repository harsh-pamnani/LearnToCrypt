package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class RoleValidationTest {

	User user;
	IValidation roleValidation;
	
	public RoleValidationTest() {
		roleValidation = new RoleValidation();
		roleValidation.setValue("Student Instructor");
		user = new User();
		user.setEmail("Orfeomarta@abc.org");
		user.setName("Orfeo Marta");
		user.setPassword("Marta@53452");
		user.setRole("Instructor");
	}
	
	@Test
	public void testIsValid() {
		assertTrue(roleValidation.isValid(user, "Marta@53452"));
		
		user.setRole("Student");
		assertTrue(roleValidation.isValid(user, "Marta@53452"));
		
		user.setRole("");
		assertFalse(roleValidation.isValid(user, "Marta@53452"));
		
		user.setRole("XYZ");
		assertFalse(roleValidation.isValid(user, "Marta@53452"));
	}
	
	@Test
	public void testGetError() {
		assertEquals("Role can not be empty.", roleValidation.getError());
		
		assertNotEquals("XYZ error has occured.", roleValidation.getError());
	}
}

package com.LearnToCrypt.Validations;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class SignUpValidationRulesTest {
	SignUpValidationRules signUpValidationRulesTest;

	public SignUpValidationRulesTest() {
		signUpValidationRulesTest = new SignUpValidationRules();
	}

	@Test
	public void testGetValidationRules() {
		// Using the form ArrayList<?> instead ArrayList<IValidation>
		// since further generic type information will be erased at runtime
		assertTrue(signUpValidationRulesTest.getValidationRules() instanceof ArrayList<?>);
	}
}

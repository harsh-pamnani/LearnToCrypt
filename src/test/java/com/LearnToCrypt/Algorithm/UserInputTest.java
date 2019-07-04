package com.LearnToCrypt.Algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class UserInputTest {

	UserInput userInput;
	
	public UserInputTest() {
		userInput = new UserInput();
	}
	
	@Test
	public void testGetKey() {
		userInput.setKey("5");
		assertEquals("5", userInput.getKey());
		
		assertNotEquals("1", userInput.getKey());
	}
	
	@Test
	public void testGetPlaintext() {
		userInput.setPlaintext("this is fun");
		assertEquals("this is fun", userInput.getPlaintext());
		
		assertNotEquals("this is not fun", userInput.getPlaintext());
	}
	
	@Test
	public void testSetKey() {
		userInput.setKey("3135");
		assertEquals("3135", userInput.getKey());
		
		assertNotEquals("999999", userInput.getKey());
	}
	
	@Test
	public void testSetPlaintext() {
		userInput.setPlaintext("I am loving it");
		assertEquals("I am loving it", userInput.getPlaintext());
		
		assertNotEquals("This is hell", userInput.getPlaintext());
	}
	
	@Test
	public void validateUserInputs() {
		userInput.setKey("");
		assertEquals("Key can't be empty", userInput.validateUserInputs());
		assertNotEquals("Key is empty", userInput.validateUserInputs());
		
		userInput.setKey("adsad");
		assertEquals("Enter only digits in the key", userInput.validateUserInputs());
		assertNotEquals("Only digits are allowed in key", userInput.validateUserInputs());
		
		userInput.setKey("7");
		userInput.setPlaintext("");
		assertEquals("Plain text can't be empty", userInput.validateUserInputs());
		assertNotEquals("Plain text is empty", userInput.validateUserInputs());
		
	}
}

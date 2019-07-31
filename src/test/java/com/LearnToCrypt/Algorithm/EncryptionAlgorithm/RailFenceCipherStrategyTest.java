package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.LearnToCrypt.Algorithm.UserInput;

public class RailFenceCipherStrategyTest {

	IEncryptionAlgorithmStrategy railFenceCipher;
	UserInput userInput;
	
	public RailFenceCipherStrategyTest() {
		railFenceCipher = new RailFenceCipherStrategy();
		userInput = new UserInput();
	}
	
	@Test
    public void testEncode() {
        String cipherText = railFenceCipher.encode("4","How are you?");
        assertEquals("Hayoroweu%%?",cipherText);
        
        assertNotEquals("asti%u%",cipherText);
    }

    @Test
    public void testGetResult() {
        railFenceCipher.encode("2","this is fun");
        String result = railFenceCipher.getResult();
        assertEquals("Plain Text: this is fun\n" + 
        		"Cipher Text: ti%sfnhsi%u%\n\n" + 
        		"NOTE: % represents space\n",result);
        
        assertNotEquals("Plain Text: Not fun\n", result);
    }
    
    @Test
    public void testGetSteps() {
    	railFenceCipher.encode("5","It is very easy to learn.");
        String result = railFenceCipher.getSteps();
        assertEquals("Steps:\n" + 
        		"I%%%e\n" + 
        		"tveta\n" + 
        		"%eaor\n" + 
        		"irs%n\n" + 
        		"syyl.\n",result);
        
        assertNotEquals("Steps: Not at all easy\n", result);
    }
    
    @Test
    public void tesKeyPlainTextValidation() throws KeyPlaintextFailureException {
		try {
			userInput.setKey("");
			railFenceCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Key can't be empty", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setKey("surrounded");
			railFenceCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Enter only digits in the key", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setKey("!@#$%#");
			railFenceCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Enter only digits in the key", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setKey("6");
			userInput.setPlaintext("");
			railFenceCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Plain text can't be empty", e.getMessage());
			assertNotEquals("Some other error messagess", e.getMessage());
		}

		try {
			userInput.setPlaintext("Life is hard");
			railFenceCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals(null, e.getMessage());
			assertNotEquals("Plain text can't be empty", e.getMessage());
		}
    }
}

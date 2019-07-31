package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.LearnToCrypt.Algorithm.UserInput;

public class PlayFairCipherStrategyTest {

	IEncryptionAlgorithmStrategy playFairCipher;
	UserInput userInput;
	
	public PlayFairCipherStrategyTest() {
		playFairCipher = new PlayFairCipherStrategy();
		userInput = new UserInput();
	}
	
	@Test
    public void testEncode() {
        String cipherText = playFairCipher.encode("place","Please encode this text");
        assertEquals("LA PC UA ZA TG KH CU BO TU AZ UY ",cipherText);
        
        assertNotEquals("QH HQ WJ IA JW MS OS ",cipherText);
    }

    @Test
    public void testGetResult() {
    	playFairCipher.encode("dream","To sure calm much most");
        
    	String result = playFairCipher.getResult();
        assertEquals("Plain Text: TO SU RE CA LM XM UC HM OS TZ \n" + 
        		"Cipher Text: UN TP EA GR OE ZE QH OH LU UY ",result);
        
        assertNotEquals("Plain Text: AB CD XZ\n", result);
    }
    
    @Test
    public void testGetSteps() {
    	playFairCipher.encode("happiness","He failed four times");
        String result = playFairCipher.getSteps();
        assertEquals("Steps:\n" + 
        		"Matrix created from the key is:\n" + 
        		"HAPIN\n" + 
        		"ESBCD\n" + 
        		"FGKLM\n" + 
        		"OQRTU\n" + 
        		"VWXYZ\n" + 
        		"\n" + 
        		"As shown above, the plain text is divided in two characters pair \n" + 
        		"and new two characters are generated from key matrix.",result);
        
        assertNotEquals("Steps: All steps are detailed\n", result);
    }
    
    @Test
    public void tesKeyPlainTextValidation() {
		try {
			userInput.setKey("");
			playFairCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
				assertEquals("Key can't be empty", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setKey("different #!%");
			playFairCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Enter only A-Z charachters in key.", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setKey("123");
			playFairCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Enter only A-Z charachters in key.", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setKey("Secure");
			userInput.setPlaintext("");
			playFairCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Plain text can't be empty", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setPlaintext("This is great 123");
			playFairCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Enter only A-Z charachters in plain text.", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setPlaintext("Some text @#!^");
			playFairCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals("Enter only A-Z charachters in plain text.", e.getMessage());
			assertNotEquals("Some other error message", e.getMessage());
		}

		try {
			userInput.setPlaintext("We can only say that it is a part of");
			playFairCipher.keyPlainTextValidation(userInput);
		} catch (KeyPlaintextFailureException e) {
			assertEquals(null, e.getMessage());
			assertNotEquals("Plain text can't be empty", e.getMessage());
		}
	}
}

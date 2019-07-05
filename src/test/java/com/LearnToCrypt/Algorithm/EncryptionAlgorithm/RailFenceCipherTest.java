package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.LearnToCrypt.Algorithm.UserInput;

public class RailFenceCipherTest {

	IEncryptionAlgorithm railFenceCipher;
	UserInput userInput;
	
	public RailFenceCipherTest() {
		railFenceCipher = new RailFenceCipher();
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
    public void tesKeyPlainTextValidation() {
    	userInput.setKey("");
    	assertEquals("Key can't be empty", railFenceCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", railFenceCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setKey("surrounded");
    	assertEquals("Enter only digits in the key", railFenceCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", railFenceCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setKey("!@#$%#");
    	assertEquals("Enter only digits in the key", railFenceCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", railFenceCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setKey("6");
    	userInput.setPlaintext("");
    	assertEquals("Plain text can't be empty", railFenceCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error messagess", railFenceCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setPlaintext("Life is hard");
    	assertEquals(null, railFenceCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Plain text can't be empty", railFenceCipher.keyPlainTextValidation(userInput));	
    }
}

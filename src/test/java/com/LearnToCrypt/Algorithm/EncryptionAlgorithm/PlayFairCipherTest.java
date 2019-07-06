package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.LearnToCrypt.Algorithm.UserInput;

public class PlayFairCipherTest {

	IEncryptionAlgorithm playFairCipher;
	UserInput userInput;
	
	public PlayFairCipherTest() {
		playFairCipher = new PlayFairCipher();
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
    	userInput.setKey("");
    	assertEquals("Key can't be empty", playFairCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", playFairCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setKey("different #!%");
    	assertEquals("Enter only A-Z charachters in key.", playFairCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", playFairCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setKey("123");
    	assertEquals("Enter only A-Z charachters in key.", playFairCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", playFairCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setKey("Secure");
    	userInput.setPlaintext("");
    	assertEquals("Plain text can't be empty", playFairCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", playFairCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setPlaintext("This is great 123");
    	assertEquals("Enter only A-Z charachters in plain text.", playFairCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", playFairCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setPlaintext("Some text @#!^");
    	assertEquals("Enter only A-Z charachters in plain text.", playFairCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Some other error message", playFairCipher.keyPlainTextValidation(userInput));
    	
    	userInput.setPlaintext("We can only say that it is a part of");
    	assertEquals(null, playFairCipher.keyPlainTextValidation(userInput));
    	assertNotEquals("Plain text can't be empty", playFairCipher.keyPlainTextValidation(userInput));	
    }
}

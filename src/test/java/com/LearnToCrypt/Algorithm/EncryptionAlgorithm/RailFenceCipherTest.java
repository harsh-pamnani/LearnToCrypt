package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class RailFenceCipherTest {

	IEncryptionAlgorithm railFenceCipher;
	
	public RailFenceCipherTest() {
		railFenceCipher = new RailFenceCipher();
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
}

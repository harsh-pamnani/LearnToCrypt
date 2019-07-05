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
        String result = railFenceCipher.encode("2","this is fun");
        assertEquals("ti%sfnhsi%u%",result);
        
        assertNotEquals("ti%u%",result);
    }

    @Test
    public void testGetResult() {
        railFenceCipher.encode("2","this is fun");
        String result = railFenceCipher.getResult();
        assertEquals("Plain Text: this is fun\n" + 
        		"Cipher Text: ti%sfnhsi%u%\n\n" + 
        		"NOTE: % represents space",result);
        
        assertNotEquals("Plain Text: Not fun\n", result);
    }
}

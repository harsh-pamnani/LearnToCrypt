package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaesarCipherTest {

    @Test
    public void testEncode() {
        IEncryptionAlgorithm cipher = new CaesarCipher();
        String result = cipher.encode("3","AbCd");
        assertEquals("DeFg",result);
    }

    @Test
    public void testGetResult() {
        IEncryptionAlgorithm cipher = new CaesarCipher();
        cipher.encode("3","AbCd");
        String result = cipher.getResult();
        assertEquals("DeFg",result);
    }

    @Test
    public void testGetSteps() {
        IEncryptionAlgorithm cipher = new CaesarCipher();
        cipher.encode("3","AbCd");
        String result = cipher.getSteps();
        assertEquals( "A -> D\nb -> e\nC -> F\nd -> g\n",result);
    }
}
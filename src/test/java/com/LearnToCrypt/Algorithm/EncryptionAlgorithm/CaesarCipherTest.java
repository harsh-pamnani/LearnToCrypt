package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;
import org.junit.Test;

import static org.junit.Assert.*;

public class CaesarCipherTest {

    UserInput userInput;
    IEncryptionAlgorithm caesarCipher;

    public CaesarCipherTest() {
        caesarCipher = new CaesarCipher();
        userInput = new UserInput();
    }

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
        assertEquals("AbCd\nDeFg",result);
    }

    @Test
    public void testGetSteps() {
        IEncryptionAlgorithm cipher = new CaesarCipher();
        cipher.encode("3","AbCd");
        String result = cipher.getSteps();
        assertEquals( "A ----> D\nb ----> e\nC ----> F\nd ----> g\n",result);
    }

    @Test
    public void tesKeyPlainTextValidation() {
        userInput.setKey("");
        assertEquals("Key can't be empty", caesarCipher.keyPlainTextValidation(userInput));
        assertNotEquals("Some other error message", caesarCipher.keyPlainTextValidation(userInput));

        userInput.setKey("aaa");
        assertEquals("Enter only numbers in key.", caesarCipher.keyPlainTextValidation(userInput));
        assertNotEquals("Some other error message", caesarCipher.keyPlainTextValidation(userInput));

        userInput.setKey("5");
        userInput.setPlaintext("");
        assertEquals("Plain text can't be empty", caesarCipher.keyPlainTextValidation(userInput));
        assertNotEquals("Some other error message", caesarCipher.keyPlainTextValidation(userInput));

        userInput.setPlaintext("This is great 123");
        assertEquals("Enter only A-Z in plain text.", caesarCipher.keyPlainTextValidation(userInput));
        assertNotEquals("Some other error message", caesarCipher.keyPlainTextValidation(userInput));

        userInput.setPlaintext("Some text @#!^");
        assertEquals("Enter only A-Z in plain text.", caesarCipher.keyPlainTextValidation(userInput));
        assertNotEquals("Some other error message", caesarCipher.keyPlainTextValidation(userInput));

        userInput.setPlaintext("We can only say that it is a part of");
        assertEquals(null, caesarCipher.keyPlainTextValidation(userInput));
        assertNotEquals("Plain text can't be empty", caesarCipher.keyPlainTextValidation(userInput));
    }
}
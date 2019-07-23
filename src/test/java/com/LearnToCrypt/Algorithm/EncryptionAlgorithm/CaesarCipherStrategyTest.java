package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;
import org.junit.Test;

import static org.junit.Assert.*;

public class CaesarCipherStrategyTest {

    UserInput userInput;
    IEncryptionAlgorithmStrategy caesarCipher;

    public CaesarCipherStrategyTest() {
        caesarCipher = new CaesarCipherStrategy();
        userInput = new UserInput();
    }

    @Test
    public void testEncode() {
        IEncryptionAlgorithmStrategy cipher = new CaesarCipherStrategy();
        String result = cipher.encode("3","AbCd");
        assertEquals("DeFg",result);
    }

    @Test
    public void testGetResult() {
        IEncryptionAlgorithmStrategy cipher = new CaesarCipherStrategy();
        cipher.encode("3","AbCd");
        String result = cipher.getResult();
        assertEquals("AbCd\nDeFg",result);
    }

    @Test
    public void testGetSteps() {
        IEncryptionAlgorithmStrategy cipher = new CaesarCipherStrategy();
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
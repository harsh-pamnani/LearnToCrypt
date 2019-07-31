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
        try {
            userInput.setKey("");
            caesarCipher.keyPlainTextValidation(userInput);
        } catch (KeyPlaintextFailureException e) {
            assertEquals("Key can't be empty", e.getMessage());
            assertNotEquals("Some other error message", e.getMessage());
        }

        try {
            userInput.setKey("aaa");
            caesarCipher.keyPlainTextValidation(userInput);
        } catch (KeyPlaintextFailureException e) {
            assertEquals("Enter only numbers in key.", e.getMessage());
            assertNotEquals("Some other error message", e.getMessage());
        }

        try {
            userInput.setKey("5");
            userInput.setPlaintext("");
            caesarCipher.keyPlainTextValidation(userInput);
        } catch (KeyPlaintextFailureException e) {
            assertEquals("Plain text can't be empty", e.getMessage());
            assertNotEquals("Some other error message", e.getMessage());
        }

        try {
            userInput.setPlaintext("This is great 123");
            caesarCipher.keyPlainTextValidation(userInput);
        } catch (KeyPlaintextFailureException e) {
            assertEquals("Enter only A-Z in plain text.", e.getMessage());
            assertNotEquals("Some other error message", e.getMessage());
        }

        try {
            userInput.setPlaintext("Some text @#!^");
            caesarCipher.keyPlainTextValidation(userInput);
        } catch (KeyPlaintextFailureException e) {
            assertEquals("Enter only A-Z in plain text.", e.getMessage());
            assertNotEquals("Some other error message", e.getMessage());
        }

        try {
            userInput.setPlaintext("We can only say that it is a part of");
            caesarCipher.keyPlainTextValidation(userInput);
        } catch (KeyPlaintextFailureException e) {
            assertEquals(null, e.getMessage());
            assertNotEquals("Plain text can't be empty", e.getMessage());
        }
    }
}
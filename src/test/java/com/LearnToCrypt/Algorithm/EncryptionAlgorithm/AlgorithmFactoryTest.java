package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class AlgorithmFactoryTest {
	
	private static final Logger logger = LogManager.getLogger(AlgorithmFactoryTest.class);
	AlgorithmFactory algorithmFactoryTest;
	
	public AlgorithmFactoryTest() {
		algorithmFactoryTest = new AlgorithmFactory();
	}
	
	@Test
	public void testCreateAlgorithm() {
		try {
			assertTrue(algorithmFactoryTest.createAlgorithm("Caesar Cipher") instanceof CaesarCipher);
			assertFalse(algorithmFactoryTest.createAlgorithm("Caesar Cipher") instanceof RailFenceCipher);
			
			assertTrue(algorithmFactoryTest.createAlgorithm("Rail Fence Cipher") instanceof RailFenceCipher);
			assertFalse(algorithmFactoryTest.createAlgorithm("Rail Fence Cipher") instanceof CaesarCipher);
			
			assertTrue(algorithmFactoryTest.createAlgorithm("Vigenere Cipher") instanceof VigenereCipher);
			assertFalse(algorithmFactoryTest.createAlgorithm("Vigenere Cipher") instanceof CaesarCipher);
			
			assertTrue(algorithmFactoryTest.createAlgorithm("Matrix Transposition Cipher") instanceof MatrixTransposeCipher);
			assertFalse(algorithmFactoryTest.createAlgorithm("Matrix Transposition Cipher") instanceof CaesarCipher);

			assertTrue(algorithmFactoryTest.createAlgorithm("Playfair Cipher") instanceof PlayFairCipher);
			assertFalse(algorithmFactoryTest.createAlgorithm("Playfair Cipher") instanceof CaesarCipher);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error occurred during algorithm factory test", e);
		}
	}
}

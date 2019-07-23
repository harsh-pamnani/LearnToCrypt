package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class AlgorithmAbstractFactoryTest {
	
	private static final Logger logger = LogManager.getLogger(AlgorithmAbstractFactoryTest.class);
	AlgorithmAbstractFactory algorithmFactoryTest;
	
	public AlgorithmAbstractFactoryTest() {
		algorithmFactoryTest = new AlgorithmAbstractFactory();
	}
	
	@Test
	public void testCreateAlgorithm() {
		try {
			assertTrue(algorithmFactoryTest.createAlgorithm("Caesar Cipher") instanceof CaesarCipherStrategy);
			assertFalse(algorithmFactoryTest.createAlgorithm("Caesar Cipher") instanceof RailFenceCipherStrategy);
			
			assertTrue(algorithmFactoryTest.createAlgorithm("Rail Fence Cipher") instanceof RailFenceCipherStrategy);
			assertFalse(algorithmFactoryTest.createAlgorithm("Rail Fence Cipher") instanceof CaesarCipherStrategy);
			
			assertTrue(algorithmFactoryTest.createAlgorithm("Vigenere Cipher") instanceof VigenereCipherStrategy);
			assertFalse(algorithmFactoryTest.createAlgorithm("Vigenere Cipher") instanceof CaesarCipherStrategy);
			
			assertTrue(algorithmFactoryTest.createAlgorithm("Matrix Transposition Cipher") instanceof MatrixTransposeCipherStrategy);
			assertFalse(algorithmFactoryTest.createAlgorithm("Matrix Transposition Cipher") instanceof CaesarCipherStrategy);

			assertTrue(algorithmFactoryTest.createAlgorithm("Playfair Cipher") instanceof PlayFairCipherStrategy);
			assertFalse(algorithmFactoryTest.createAlgorithm("Playfair Cipher") instanceof CaesarCipherStrategy);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error occurred during algorithm factory test", e);
		}
	}
}

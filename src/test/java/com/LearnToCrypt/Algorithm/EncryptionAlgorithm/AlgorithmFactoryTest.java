package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AlgorithmFactoryTest {
	
	AlgorithmFactory algorithmFactoryTest;
	
	public AlgorithmFactoryTest() {
		algorithmFactoryTest = new AlgorithmFactory();
	}
	
	@Test
	public void testCreateUser() {
		assertTrue(algorithmFactoryTest.createAlgorithm("Caesar Cipher") instanceof CaesarCipher);
		assertFalse(algorithmFactoryTest.createAlgorithm("Caesar Cipher") instanceof RailFenceCipher);
		
		assertTrue(algorithmFactoryTest.createAlgorithm("Rail Fence Cipher") instanceof RailFenceCipher);
		assertFalse(algorithmFactoryTest.createAlgorithm("Rail Fence Cipher") instanceof CaesarCipher);
		
		assertEquals(algorithmFactoryTest.createAlgorithm("abcd"),null);
	}
}

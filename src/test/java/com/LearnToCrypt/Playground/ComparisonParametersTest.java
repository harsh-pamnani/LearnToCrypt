package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.CaesarCipherStrategy;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.MatrixTransposeCipherStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComparisonParametersTest {
	private static final Logger logger = LogManager.getLogger(ComparisonParametersTest.class);
	private IComparisonParameters parameters;
	private IEncryptionAlgorithmStrategy caesarCipherStrategy;
	private IEncryptionAlgorithmStrategy matrixTransposeStrategy;

	public ComparisonParametersTest() {
		logger.info("Starting tests");
		logger.info("Start - test setup");
		parameters = new ComparisonParameters();
		caesarCipherStrategy = new CaesarCipherStrategy();
		matrixTransposeStrategy = new MatrixTransposeCipherStrategy();
		parameters.clearInputParams();
		parameters.addAlgorithm(caesarCipherStrategy, "3", "Caeser Cipher");
		parameters.addAlgorithm(matrixTransposeStrategy, "3,1,2", "Matrix Transposition Cipher");
		logger.info("End - test setup");
	}

	@Test
	public void testHasNextAlgorithmName() {
		assertTrue(parameters.hasNextAlgorithmName());
		String algorithm = parameters.getNextAlgorithmName();
		assertTrue(parameters.hasNextAlgorithmName());
		algorithm = parameters.getNextAlgorithmName();
		assertFalse(parameters.hasNextAlgorithmName());
		assertTrue(parameters.hasNextAlgorithmName());
	}

	@Test
	public void testGetNextAlgorithmName() {
		String algorithm = parameters.getNextAlgorithmName();
		assertEquals("Caeser Cipher", algorithm);
		algorithm = parameters.getNextAlgorithmName();
		assertEquals("Matrix Transposition Cipher", algorithm);
	}

	@Test
	public void testGetKey() {
		String key = parameters.getKey("Caeser Cipher");
		assertEquals("3", key);
		key = parameters.getKey("Matrix Transposition Cipher");
		assertEquals("3,1,2", key);
	}

	@Test
	public void testGetEncryptionAlgorithm() {
		IEncryptionAlgorithmStrategy strategy = parameters.getEncryptionAlgorithm("Caeser Cipher");
		assertTrue(strategy instanceof CaesarCipherStrategy);
		strategy = parameters.getEncryptionAlgorithm("Matrix Transposition Cipher");
		assertTrue(strategy instanceof MatrixTransposeCipherStrategy);
	}

	@Test
	public void testClearInputParams() {
		assertTrue(parameters.hasNextAlgorithmName());
		parameters.clearInputParams();
		assertFalse(parameters.hasNextAlgorithmName());
	}

}

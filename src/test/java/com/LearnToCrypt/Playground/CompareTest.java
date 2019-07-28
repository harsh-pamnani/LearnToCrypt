package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.CaesarCipherStrategy;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CompareTest {

	private static final Logger logger = LogManager.getLogger(CompareTest.class);
	private ICompare compare;
	private IComparisonParameters parameters;
	private IEncryptionAlgorithmStrategy caesarCipherStrategy;
	private IComparisonResultSet resultSet;

	public CompareTest() {
		logger.info("Starting tests");
		logger.info("Start - test setup");
		parameters = new ComparisonParameters();
		caesarCipherStrategy = new CaesarCipherStrategy();
		parameters.clearInputParams();
		parameters.addAlgorithm(caesarCipherStrategy, "3", "Caeser Cipher");
		parameters.setPlaintext("I Love TDD");
		compare = new Compare();

		logger.info("End - test setup");
	}

	@Test
	public void testCompareAlgorithms() {
		resultSet = compare.compareAlgorithms(parameters);
		IComparisonResult result = resultSet.getNextResult();
		assertEquals("Caeser Cipher", result.getName());
		assertEquals("L Oryh WGG", result.getEncryptedText());
		assertEquals(10, result.getEncryptedTextLength());
		assertEquals(10,result.getPlaintextLength());
		assertFalse(result.hasError());
	}
}

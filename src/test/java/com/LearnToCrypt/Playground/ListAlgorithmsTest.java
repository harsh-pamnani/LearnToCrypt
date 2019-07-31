package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.*;
import com.LearnToCrypt.DAO.AlgorithmDAOFactoryMock;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ListAlgorithmsTest {

	private static final Logger logger = LogManager.getLogger(ListAlgorithmsTest.class);
	IAlgorithmDAO algorithmDAO;
	IAlgorithmFactory factory;
	IListAlgorithms algorithms;

	public ListAlgorithmsTest() {
		logger.info("Starting tests");
		logger.info("Start - test setup");
		algorithmDAO = new AlgorithmDAOFactoryMock();
		factory = new AlgorithmAbstractFactory();
		algorithms = new ListAlgorithms(algorithmDAO, factory);
		logger.info("End - test setup");
	}

	@Test
	public void testGetAlgorithmWithName() {
		IEncryptionAlgorithmStrategy algorithmStrategy = algorithms.getAlgorithmWithName("Caesar Cipher");
		assertTrue(algorithmStrategy instanceof CaesarCipherStrategy);
		algorithmStrategy = algorithms.getAlgorithmWithName("Matrix Transposition Cipher");
		assertTrue(algorithmStrategy instanceof MatrixTransposeCipherStrategy);
	}
}

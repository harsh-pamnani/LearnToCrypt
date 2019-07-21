package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.Algorithm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmDAOTest {

	IDAOAbstractFactory daoAbstractFactory;
	IAlgorithmDAO algorithmDAO;

	public AlgorithmDAOTest() {
		daoAbstractFactory = new DAOAbstractFactory();
		algorithmDAO = daoAbstractFactory.createAlgorithmDAO();
	}

	@Test
	public void testGetAlgorithm() {

		Algorithm algorithm = algorithmDAO.getAlgorithm("Caesar Cipher");
		assertEquals("Caesar Cipher", algorithm.getName());
		assertEquals("Caesar_cipher.png", algorithm.getImage());
		assertEquals(1, algorithm.getLevel());
		
		algorithm = algorithmDAO.getAlgorithm("Matrix Transposition Cipher");
		assertEquals("Matrix Transposition Cipher", algorithm.getName());
		assertEquals("Matrix_Cipher.png", algorithm.getImage());
		assertEquals(2, algorithm.getLevel());
		
		algorithm = algorithmDAO.getAlgorithm("Playfair Cipher");
		assertEquals("Playfair Cipher", algorithm.getName());
		assertEquals("Playfair_Cipher.PNG", algorithm.getImage());
		assertEquals(2, algorithm.getLevel());
		
		algorithm = algorithmDAO.getAlgorithm("Rail Fence Cipher");
		assertEquals("Rail Fence Cipher", algorithm.getName());
		assertEquals("Rail_Fence_Cipher.png", algorithm.getImage());
		assertEquals(1, algorithm.getLevel());
		
		algorithm = algorithmDAO.getAlgorithm("Vigenere Cipher");
		assertEquals("Vigenere Cipher", algorithm.getName());
		assertEquals("Vigenere_Cipher.png", algorithm.getImage());
		assertEquals(2, algorithm.getLevel());
	}
}

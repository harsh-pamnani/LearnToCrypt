package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.Algorithm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmDAOTest {
    @Test
    public void testGetAlgorithm(){
        IDAOAbstractFactory daoAbstractFactory = new DAOAbstractFactory();
        IAlgorithmDAO algorithmDAO = daoAbstractFactory.createAlgorithmDAO();
        Algorithm algorithm = algorithmDAO.getAlgorithm("Caesar Cipher");
        assertEquals(algorithm.getName(),"Caesar Cipher");
        assertEquals(algorithm.getDescription(),"The action of a Caesar cipher is to replace each plaintext letter with a different one a fixed number of places down the alphabet. The cipher illustrated here uses a left shift of three, so that (for example) each occurrence of E in the plaintext becomes B in the ciphertext.");
        assertEquals(algorithm.getImage(),"Caesar_cipher.png");
        assertEquals(algorithm.getLevel(),1);
    }

}

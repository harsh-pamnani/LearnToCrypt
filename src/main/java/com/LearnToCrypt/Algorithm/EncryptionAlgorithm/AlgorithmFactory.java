package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlgorithmFactory implements IAlgorithmFactory{
	
    private static final Logger logger = LogManager.getLogger(AlgorithmFactory.class);
	
    @Override
    public IEncryptionAlgorithmStrategy createAlgorithm(String name) throws NoSuchAlgorithmException {
        IEncryptionAlgorithmStrategy algorithm = null;
        switch(name) {
            case "Caesar Cipher":
                algorithm = new CaesarCipherStrategy();
                break;
            case "Vigenere Cipher":
                algorithm = new VigenereCipherStrategy();
                break;
            case "Matrix Transposition Cipher":
                algorithm = new MatrixTransposeCipherStrategy();
                break;
            case "Playfair Cipher":
                algorithm = new PlayFairCipherStrategy();
                break;
            case "Rail Fence Cipher":
            	algorithm = new RailFenceCipherStrategy();
                break;
            default:
            	logger.error("Unknown algorithm request : " + name);
                throw new NoSuchAlgorithmException();
        }
        return algorithm;
    }
}

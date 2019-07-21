package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.app.LearnToCryptApplication;

public class AlgorithmFactory implements IAlgorithmFactory{
	
    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	
    @Override
    public IEncryptionAlgorithm createAlgorithm(String name) throws NoSuchAlgorithmException {
        IEncryptionAlgorithm algorithm = null;
        switch(name) {
            case "Caesar Cipher":
                algorithm = new CaesarCipher();
                break;
            case "Vigenere Cipher":
                algorithm = new VigenereCipher();
                break;
            case "Matrix Transposition Cipher":
                algorithm = new MatrixTransposeCipher();
                break;
            case "Playfair Cipher":
                algorithm = new PlayFairCipher();
                break;
            case "Rail Fence Cipher":
            	algorithm = new RailFenceCipher();
                break;
            default:
            	logger.error("Unknown algorithm request : " + name);
                throw new NoSuchAlgorithmException();
        }
        return algorithm;
    }
}

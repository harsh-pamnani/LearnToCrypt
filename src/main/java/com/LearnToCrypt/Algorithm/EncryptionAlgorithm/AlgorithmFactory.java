package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

public class AlgorithmFactory implements IAlgorithmFactory{
    @Override
    public IEncryptionAlgorithm createAlgorithm(String name) {
        IEncryptionAlgorithm algorithm = null;
        switch(name) {
            case "Caesar Cipher":
                algorithm = new CaesarCipher();
                break;
            case "Vigenere Cipher":
                // TODO: I don't know
                break;
            case "Matrix Transportation Cipher":
                // TODO: I don't know
                break;
            case "Playfair Cipher":
                // TODO: I don't know
                break;
            case "Rail Fence Cipher":
            	algorithm = new RailFenceCipher();
                break;
            default:
                return null;

        }
        return algorithm;
    }
}

package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import java.security.NoSuchAlgorithmException;

public interface IAlgorithmFactory {
    public IEncryptionAlgorithmStrategy createAlgorithm(String name) throws NoSuchAlgorithmException;
}

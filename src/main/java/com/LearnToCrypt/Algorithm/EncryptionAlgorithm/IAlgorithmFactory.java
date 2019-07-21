package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import java.security.NoSuchAlgorithmException;

public interface IAlgorithmFactory {
    public IEncryptionAlgorithm createAlgorithm(String name) throws NoSuchAlgorithmException;
}

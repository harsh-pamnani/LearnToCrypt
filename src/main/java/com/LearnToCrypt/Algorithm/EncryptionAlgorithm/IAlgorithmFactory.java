package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

public interface IAlgorithmFactory {
    public IEncryptionAlgorithm createAlgorithm(String name);
}

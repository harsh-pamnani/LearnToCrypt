package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

public interface IEncryptionAlgorithm {

    String encode(String key,String plaintext);
    String getResult();
    String getSteps();

}

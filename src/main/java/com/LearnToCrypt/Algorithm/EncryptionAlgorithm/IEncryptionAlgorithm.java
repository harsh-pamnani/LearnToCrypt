package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;

public interface IEncryptionAlgorithm {

    String encode(String key,String plaintext);
    String getResult();
    String getSteps();

    public String keyPlainTextValidation(UserInput userInput);
}

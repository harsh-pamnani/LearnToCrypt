package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;

public interface IEncryptionAlgorithmStrategy {
	public String encode(String key,String plaintext);
    public String getResult();
    public String getSteps();
    public String keyPlainTextValidation(UserInput userInput);
}

package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;

public interface IEncryptionAlgorithmStrategy {
    public static final String ERROR_KEY_EMPTY = "Key can't be empty";
    public static final String ERROR_KEY_ONLY_A_TO_Z = "Enter only A-Z charachters in key.";
    public static final String ERROR_PLAIN_TEXT_EMPTY = "Plain text can't be empty";
    public static final String ERROR_PLAIN_TEXT_A_TO_Z = "Enter only A-Z charachters in plain text.";
    public static final String ERROR_KEY_ONLY_DIGITS = "Enter only digits in the key";

	public String encode(String key,String plaintext);
    public String getResult();
    public String getSteps();
    public void keyPlainTextValidation(UserInput userInput) throws KeyPlaintextFailureException;
    public String getName();
}

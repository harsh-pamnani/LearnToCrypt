package com.LearnToCrypt.Algorithm.Cipher;

public interface ICipher {

    String encode(String key,String plaintext);
    String getResult();
    String getSteps();

}

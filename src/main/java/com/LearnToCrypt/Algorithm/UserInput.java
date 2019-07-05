package com.LearnToCrypt.Algorithm;

public class UserInput {
    private String key;
    private String plaintext;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
    
    public String validateUserInputs() {
    	String formError = null;
    	
    	if(key.isEmpty()) {
    		formError ="Key can't be empty";
    	} else if (!key.matches("[0-9]+")) {
    		formError = "Enter only digits in the key";
    	} else if(plaintext.isEmpty()) {
    		formError = "Plain text can't be empty";
    	}
    	
    	return formError;
    }
}

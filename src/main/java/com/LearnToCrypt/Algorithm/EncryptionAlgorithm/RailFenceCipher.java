package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.Algorithm.UserInput;
import com.LearnToCrypt.app.LearnToCryptApplication;

public class RailFenceCipher implements IEncryptionAlgorithm {

	private String result = "Cipher Text: ";
	private String steps = "Steps:\n";
	private String plaintext = null;
	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	@Override
	public String encode(String key, String plaintext) {
		logger.info("Rail Fence Cipher: Encoding starterd with key: " + key 
				+ "and plaintext: " + plaintext);
		this.plaintext = "Plain Text: " + plaintext;

		int keyToEncrypt = Integer.parseInt(key);
		
		plaintext = formatPlaintext(plaintext, keyToEncrypt);
		
		int matrixWidth = plaintext.length() / keyToEncrypt;
		int matrixHeight = keyToEncrypt;
		
		char ciphertextMatrix[][] = new char[matrixHeight][matrixWidth];
		
		int index = 0;
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < keyToEncrypt; j++) {
				ciphertextMatrix[j][i] = plaintext.charAt(index++);	
			}
		}
		
		String ciphertext = "";
		for (int i = 0; i < keyToEncrypt; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				ciphertext += ciphertextMatrix[i][j];
				steps += ciphertextMatrix[i][j];
			}
			steps += "\n";
		}

		result += (ciphertext + "\n\nNOTE: % represents space\n");
		logger.info("Rail Fence Cipher: Encoding done. Cipher text: " + ciphertext 
				+ "and result: " + result);
		
		return ciphertext;
	}

	@Override
	public String getResult() {
		return plaintext + "\n" + result;
	}

	@Override
	public String getSteps() {
		return steps;
	}

	@Override
	public String keyPlainTextValidation(UserInput userInput) {
		String formError = null;

    	if(userInput.getKey().isEmpty()) {
    		formError ="Key can't be empty";
    	} else if(!userInput.getKey().matches("[0-9]+")) {
    		formError = "Enter only digits in the key";
    	} else if(userInput.getPlaintext().isEmpty()) {
    		formError = "Plain text can't be empty";
    	}

    	if (formError == null) {
			logger.info("Rail Fence Cipher: Key Validated Successfully");
		} else {
			logger.error("Rail Fence Cipher: Key Validation Error: " + formError);
		}
    	
    	return formError;
	}
	
	private String formatPlaintext(String plaintext, int keyToEncrypt) {

		plaintext = plaintext.replace(" ", "%");
		int plaintextLength = plaintext.length();
		
		int reminder = 0;
		if(plaintextLength < keyToEncrypt) {
			reminder = keyToEncrypt - plaintextLength;
		} else {
			reminder = plaintextLength % keyToEncrypt;
		}
		
		for (int i = 0; i < reminder; i++) {
			plaintext += "%";
		}

		logger.info("Rail Fence Cipher: Plain text formatting done");
		
		return plaintext;
	}
}

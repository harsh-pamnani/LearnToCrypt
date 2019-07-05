package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;

public class RailFenceCipher implements IEncryptionAlgorithm {

	private String result = "Cipher Text: ";
	private String steps = "";
	private String plaintext = null;

	@Override
	public String encode(String key, String plaintext) {
		this.plaintext = "Plain Text: " + plaintext;

		int keyToEncrypt = Integer.parseInt(key);

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

		plaintextLength = plaintext.length();
		int matrixWidth = plaintextLength / keyToEncrypt;
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
			}
		}

		result += (ciphertext + "\n\nNOTE: % represents space");
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
		return null;
	}
}

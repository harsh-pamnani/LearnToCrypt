package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VigenereCipher implements IEncryptionAlgorithm {
	private final char startChar = 'A';
	private final char endChar = 'Z';
	private final String resultTxt = "Cipher Text: ";
	private String steps = "Steps:\n";
	private String unencryptedPlaintext = null;
	private String encryptionKey = null;
	private String encryptedCipherText = null;
	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	@Override
	public String encode(String key, String plaintext) {
		String ciphertext = "";
		logger.info("Start: encoding " + plaintext + " with key " + key);
		unencryptedPlaintext = plaintext.toUpperCase();
		encryptionKey = key.toUpperCase();
		char[] encryptedChars = new char[plaintext.length()];
		for(int textIndex = 0, keyIndex = 0; textIndex < unencryptedPlaintext.length(); textIndex++, keyIndex++ ) {
			char textChar = unencryptedPlaintext.charAt(textIndex);
			if (keyIndex >= encryptionKey.length()) {
				keyIndex = keyIndex - encryptionKey.length();
			}
			char keyChar = encryptionKey.charAt(keyIndex);
			int textCharAscii = (int) textChar;
			int keyAscii = (int) keyChar;
			int keyCharFromA = keyAscii - (int)startChar;
			int encryptedCharAscii;
			if (textCharAscii >= (int)startChar && textCharAscii <= (int)endChar) {
				encryptedCharAscii = textCharAscii + keyCharFromA;
				if (encryptedCharAscii > (int) endChar) {
					encryptedCharAscii -= ((int)endChar - (int) startChar);
				}
			} else {
				encryptedCharAscii = textCharAscii;
			}
			char encryptedChar = (char) encryptedCharAscii;
			encryptedChars[textIndex] = encryptedChar;
			logger.info("Encoded: " + textChar + " --> " + encryptedChar);
			steps+= ("\nEncoded: " + textChar + " --> " + encryptedChar);
		}
		ciphertext = String.copyValueOf(encryptedChars);
		logger.info("End: encoding " + plaintext + " with key " + key + " to " + ciphertext);
		encryptedCipherText = ciphertext;
		return ciphertext;
	}

	@Override
	public String getResult() {
		return resultTxt + encryptedCipherText;
	}

	@Override
	public String getSteps() {
		return steps;
	}

	@Override
	public String keyPlainTextValidation(UserInput userInput) {

		logger.info("Validating key for Vigenere Cipher. Key: " + userInput.getKey());
		String formError = null;

		if (userInput.getKey().isEmpty()) {
			formError = "Key can't be empty";
		} else if (!userInput.getKey().matches("[A-Za-z ]+")) {
			formError = "Enter only A-Z characters in key.";
		} else if (userInput.getPlaintext().isEmpty()) {
			formError = "Plain text can't be empty";
		} else if (!userInput.getPlaintext().matches("[A-Za-z ]+")) {
			formError = "Enter only A-Z characters in plain text.";
		}

		if (formError == null) {
			logger.info("Key Validated Successfully");
		} else {
			logger.error("Key Validation Error: " + formError);
		}
		return formError;
	}
}

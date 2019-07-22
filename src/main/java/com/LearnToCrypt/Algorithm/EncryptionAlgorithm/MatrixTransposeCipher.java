package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class MatrixTransposeCipher implements IEncryptionAlgorithm {

	private final String resultTxt = "Cipher Text: ";
	private final String keyRegex = "([0-9],)+[0-9]";
	private final String plaintextRegex = "[A-Za-z ]+";
	private String steps = "Steps:\n";
	private String unencryptedPlaintext = null;
	private String encryptionKey = null;
	private String encryptedCipherText = null;
	private static final Logger logger = LogManager.getLogger(MatrixTransposeCipher.class);

	@Override
	public String encode(String key, String plaintext) {
		logger.info("Start: encoding " + plaintext + " with key " + key);
		unencryptedPlaintext = plaintext;
		encryptionKey = key;
		String ciphertext = "";
		int[] keyNums = parseKey(encryptionKey);
		if (keyNums == null) {
			logger.error("Error parsing key");
			return null;
		}
		int numberOfColumns = getNumColumns(keyNums);
		int numberOfRows = unencryptedPlaintext.length()/numberOfColumns;
		char[][] textMatrix = parsePlaintext(unencryptedPlaintext, numberOfColumns);
		for (int i = 0; i < numberOfColumns; i++) {
			for (int j = 0; j < numberOfRows; j++) {
				ciphertext += textMatrix[j][keyNums[i]];
			}
			steps += ("\nAdding characters in column " + keyNums[i] + "\n Ciphertext = " + ciphertext);
		}
		logger.info("End: encoding " + plaintext + " with key " + key + " to " + ciphertext);
		encryptedCipherText = ciphertext;
		return encryptedCipherText;
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
		logger.info("Validating key for Matrix Transposition Cipher. Key: " + userInput.getKey());
		String formError = null;

		if (userInput.getKey().isEmpty()) {
			formError = "Key can't be empty";
		} else if (!userInput.getKey().matches(keyRegex)) {
			formError = "Key only accepts the format of numbers (0-9) separated by comma. e.g. 1,2,3,4";
		} else if (userInput.getPlaintext().isEmpty()) {
			formError = "Plain text can't be empty";
		} else if (!userInput.getPlaintext().matches(plaintextRegex)) {
			formError = "Enter only A-Z charachters in plain text.";
		} else if (!validateKeySeries(userInput.getKey())) {
			formError = "Key should contain a continuous series of numbers. e.g. 4,1,3,2";
		}

		if (formError == null) {
			logger.info("Key Validated Successfully");
		} else {
			logger.error("Key Validation Error: " + formError);
		}
		return formError;
	}

	private int[] parseKey(String key) {
		logger.info("Parsing string key to numbers");
		String[] keyChars = key.split(",");
		int[] keyNums = new int[keyChars.length];
		try {
			for (int i = 0; i < keyChars.length; i++) {
				keyNums[i] = Integer.parseInt(keyChars[i]) - 1;
			}
		} catch (NumberFormatException e) {
			logger.error("Error parsing key: " + e.getMessage());
			return null;
		}
		return keyNums;
	}

	private char[][] parsePlaintext(String plaintext, int numberOfColumns) {
		logger.info("Converting plaintext to a 2-D Matrix");
		String formattedText = reformatPlaintext(plaintext, numberOfColumns);
		int numberOfRows = formattedText.length()/numberOfColumns;
		char[][] textMatrix = new char[numberOfRows][numberOfColumns];
		for (int i = 0, col = 0; i < formattedText.length(); i++, col++) {
			int row = i/numberOfColumns;
			if (col >= numberOfColumns) {
				col = col - numberOfColumns;
			}
			textMatrix[row][col] = formattedText.charAt(i);
		}

		logger.info("Converted plaintext to a 2-D Matrix");
		return textMatrix;
	}

	private String reformatPlaintext(String plaintext, int numberOfColumns) {
		logger.info("Reformatting plaintext for Matrix Transposition Cipher");
		int excess = numberOfColumns - (plaintext.length() % numberOfColumns);
		plaintext = plaintext.replaceAll(" ", "%");
		for (int i = 0; i < excess; i++) {
			plaintext += "%";
		}
		logger.info("Plaintext Reformatted for Matrix Transposition Cipher");
		return plaintext;
	}

	private int getNumColumns(int[] key) {
		logger.info("Calculating number of columns in the matrix");
		int numCols = 0;
		numCols = key[0];
		for (int num: key) {
			if (numCols < num) {
				numCols = num;
			}
		}
		numCols += 1;
		logger.info("Number of columns in the matrix: " + String.valueOf(numCols));
		return numCols;
	}

	private boolean validateKeySeries (String key) {
		logger.info("Validation for key being a continuous series");
		int[] keyNums= parseKey(key);
		boolean isValid = false;
		ArrayList<Boolean> series = new ArrayList<>();
		for (int i = 0; i < keyNums.length; i++) {
			series.add(false);
		}
		try {
			for (int i = 0; i < keyNums.length; i++) {
				series.set(keyNums[i], true);
			}
			if (series.contains(false)) {
				isValid = false;
			} else {
				isValid = true;
			}
		} catch (IndexOutOfBoundsException e) {
			logger.error("Error in key format");
			isValid = false;
		}
		return isValid;
	}
}

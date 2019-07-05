package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

import com.LearnToCrypt.Algorithm.UserInput;

public class PlayFairCipher implements IEncryptionAlgorithm {

	private static final String ALL_CHARS = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	private static final String CHARACTER_TO_REPLACE = "X";
	private static final int MATRIX_SIZE = 5;
	private String result = "Cipher Text: ";
	private String steps = "Steps:\nMatrix created from the key is:\n";
	private String plaintext = "Plain Text: ";
	private HashMap<String, String> repeatedCharacters;
	private char[][] keyMatrix;
	
	public PlayFairCipher() {
		repeatedCharacters = new HashMap<String, String>();
		keyMatrix = new char[MATRIX_SIZE][MATRIX_SIZE];
		setRepeatedCharactersList();
	}
	
	@Override
	public String encode(String key, String plaintext) {
		
		generateKeyMatrixFromKey(key);
		plaintext = cleanPlainText(plaintext);
		ArrayList<String> stringDividedInTwoChars = dividePlainTextInTwoCharsPair(plaintext);
		
		String ciphertext = "";
		for(String twoChars: stringDividedInTwoChars) {
			ArrayList<Integer> newPoints = getNewCoordinates(twoChars);
			
			int newFirstX = newPoints.get(0);
			int newFirstY = newPoints.get(1);
			int newSecondX = newPoints.get(2);
			int newSecondY = newPoints.get(3);
						
			char newFirstChar = keyMatrix[newFirstX][newFirstY];
			char newSecondChar = keyMatrix[newSecondX][newSecondY];
			
			System.out.println("OLD: " + twoChars);
			System.out.println("NEW: " + newFirstChar + newSecondChar);
			this.plaintext += (twoChars + " ");
			ciphertext += (newFirstChar + "" + newSecondChar + " ");
		}
		
		result += ciphertext;
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
    	} else if(!userInput.getKey().matches("[A-Za-z ]+")) {
    		formError = "Enter only A-Z charachters in key.";
    	} else if(userInput.getPlaintext().isEmpty()) {
    		formError = "Plain text can't be empty";
    	} 
    	
    	return formError;
	}
	
	// Method to generate the key matrix from the provided key
	private void generateKeyMatrixFromKey(String key) {
		key = key.toUpperCase().replaceAll(" ", "") + ALL_CHARS;
		key = key.replaceAll("J", "I");
		
		String finalKey = "";
		for(int i=0; i < key.length(); i++) {
			if(!finalKey.contains(key.charAt(i)+"")) {
				finalKey += key.charAt(i);
			}
		}
		
		int index = 0;
		for(int i=0; i < MATRIX_SIZE; i++) {
			for(int j=0; j < MATRIX_SIZE; j++) {
				keyMatrix[i][j] = finalKey.charAt(index);
				steps += finalKey.charAt(index);
				index++;
			}
			steps += "\n";
		}
		
		steps += ("\nAs shown above, the plain text is divided in two characters pair \n"
				+ "and new two characters are generated from key matrix.");
	}
	
	// Method to clean the plaintext according to algorithm's requirements
	private String cleanPlainText(String plaintext) {
		String cleanedPlainText = plaintext.toUpperCase().replaceAll(" ", "");
		
		for(String repeatedCharKey: repeatedCharacters.keySet()) {
			cleanedPlainText = cleanedPlainText.replaceAll(repeatedCharKey, repeatedCharacters.get(repeatedCharKey));
		}
		
		if(cleanedPlainText.length() % 2 != 0) {
			cleanedPlainText += "Z";
		}
		
		return cleanedPlainText;
	}

	// Method to divide the plain text into pair of two characters
	private ArrayList<String> dividePlainTextInTwoCharsPair(String plaintext) {
		String tempString = "";
		
		ArrayList<String> stringDividedInTwoChars = new ArrayList<String>();
		
		for(int index = 0; index < plaintext.length(); index++) {
			tempString += plaintext.charAt(index);
			if(index % 2 != 0) {
				stringDividedInTwoChars.add(tempString);
				tempString="";
			}
		}
		
		return stringDividedInTwoChars;
	}
	
	// Method to find X and Y coordinates of character
	private String findXY(char character) {
		String XY = "";
		
		for(int i = 0; i < MATRIX_SIZE; i++) {
			for(int j = 0; j < MATRIX_SIZE; j++) {
				if((keyMatrix[i][j]+"").equals(""+character)) {
					XY = (i + "" + j);
				}
			}
		}
		
		return XY;
	}
	
	// Method to create HashMap for repeated characters and the characters to replace them
	private void setRepeatedCharactersList() {
		for(int index = 0; index < ALL_CHARS.length(); index++) {
			String character = ALL_CHARS.charAt(index) + "";
			String repeatedChar = character + character;
			String replaceRepeatedChar = character + CHARACTER_TO_REPLACE +character;
			repeatedCharacters.put(repeatedChar, replaceRepeatedChar);
		}
	}
	
	// Method to get the new coordinates of the given two characters
	private ArrayList<Integer> getNewCoordinates(String twoChars) {
		String firstCharXY = findXY(twoChars.charAt(0));
		String secondCharXY = findXY(twoChars.charAt(1));
				
		int oldFirstX = Integer.parseInt(firstCharXY.substring(0, 1));
		int oldFirstY = Integer.parseInt(firstCharXY.substring(1));
		int oldSecondX = Integer.parseInt(secondCharXY.substring(0, 1));
		int oldSecondY = Integer.parseInt(secondCharXY.substring(1));

		ArrayList<Integer> newCoordinates = new ArrayList<Integer>();
		
		int newFirstX, newFirstY, newSecondX, newSecondY;
		
		newFirstX = oldFirstX;
		newSecondX = oldSecondX;
		newFirstY = oldFirstY;
		newSecondY = oldSecondY;
		
		if(oldFirstX == oldSecondX && oldFirstY == oldSecondY) {
			// Do nothing
		} else if (oldFirstX == oldSecondX) {
			newFirstY = ((oldFirstY + 1) % MATRIX_SIZE);				
			newSecondY = ((oldSecondY + 1) % MATRIX_SIZE);
		} else if (oldFirstY == oldSecondY) {
			newFirstX = ((oldFirstX + 1) % MATRIX_SIZE);
			newSecondX = ((oldSecondX + 1) % MATRIX_SIZE);
		} else {
			newFirstY = oldSecondY;
			newSecondY = oldFirstY;
		}
		
		newCoordinates.add(newFirstX);
		newCoordinates.add(newFirstY);
		newCoordinates.add(newSecondX);
		newCoordinates.add(newSecondY);
		
		return newCoordinates;
	}
}

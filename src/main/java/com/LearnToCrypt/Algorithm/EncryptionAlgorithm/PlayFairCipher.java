package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

import com.LearnToCrypt.Algorithm.UserInput;

public class PlayFairCipher implements IEncryptionAlgorithm {

	private static final String ALL_CHARS = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	private static final String CHARACTER_TO_REPLACE = "X";
	private String result = "Cipher Text: ";
	private String steps = "Steps:\nMatrix created from the key is:\n";
	private String plaintext = null;
	private HashMap<String, String> repeatedCharacters;
	private char[][] keyMatrix;
	
	public PlayFairCipher() {
		repeatedCharacters = new HashMap<String, String>();
		keyMatrix = new char[5][5];
		setRepeatedCharactersList();
	}
	
	@Override
	public String encode(String key, String plaintext) {
		this.plaintext = "Plain Text: " + plaintext;
		String ciphertext = "";
		
		String combinedKey = key.toUpperCase().replaceAll(" ", "") + ALL_CHARS;
		combinedKey = combinedKey.replaceAll("J", "I");
		String finalKey = "";
		
		for(int i=0; i < combinedKey.length(); i++) {
			if(!finalKey.contains(combinedKey.charAt(i)+"")) {
				finalKey += combinedKey.charAt(i);
			}
		}
		
		
		
		int index = 0;
		for(int i=0; i < 5; i++) {
			for(int j=0; j < 5; j++) {
				keyMatrix[i][j] = finalKey.charAt(index);
				steps += finalKey.charAt(index);
				index++;
			}
			steps += "\n";
		}
		
		
		
		
		
		
		
		String plainTextString = plaintext.toUpperCase().replaceAll(" ", "");
		for(String repeatedCharKey: repeatedCharacters.keySet()) {
			plainTextString = plainTextString.replaceAll(repeatedCharKey, repeatedCharacters.get(repeatedCharKey));
		}
		
		if(plainTextString.length() % 2 != 0) {
			plainTextString += "Z";
		}
				
		String tempString = "";
		ArrayList<String> stringDividedInTwoChars = new ArrayList<String>();
		for(index = 0; index < plainTextString.length(); index++) {
			tempString += plainTextString.charAt(index);
			if(index % 2 != 0) {
				stringDividedInTwoChars.add(tempString);
				tempString="";
			}
		}
		
		for(String twoChars: stringDividedInTwoChars) {
			
			String firstCharXY = findXY(twoChars.charAt(0));
			String secondCharXY = findXY(twoChars.charAt(1));
			
//			System.out.println("OLD POINTS = First: " + firstCharXY + " & Second: " + secondCharXY);
			
			int firstX = Integer.parseInt(firstCharXY.substring(0, 1));
			int firstY = Integer.parseInt(firstCharXY.substring(1));
			int secondX = Integer.parseInt(secondCharXY.substring(0, 1));
			int secondY = Integer.parseInt(secondCharXY.substring(1));
			
			int newFirstX, newFirstY, newSecondX, newSecondY;
			
			if(firstX == secondX && firstY == secondY) {
				//System.out.println("do nothing");
				
				newFirstX = firstX;
				newSecondX = secondX;
				newFirstY = firstY;
				newSecondY = secondY;
			} else if (firstX == secondX) {
				//System.out.println("Keep row num same and increase column by one");
				
				newFirstX = firstX;
				newSecondX = secondX;
				newFirstY = increaseAndResetIfMax(firstY);				
				newSecondY = increaseAndResetIfMax(secondY);
			} else if (firstY == secondY) {
				//System.out.println("Keep column num same and increase row by one");
				
				newFirstY = firstY;
				newSecondY = secondY;
				newFirstX = increaseAndResetIfMax(firstX);
				newSecondX = increaseAndResetIfMax(secondX);
			} else {
				newFirstX = firstX;
				newSecondX = secondX;
				newFirstY = secondY;
				newSecondY = firstY;
			}
			
//			System.out.println("OLD POINTS = First: " + newFirstX+ "" + newFirstY + " & Second: " + secondCharXY);
			
			char newFirstChar = keyMatrix[newFirstX][newFirstY];
			char newSecondChar = keyMatrix[newSecondX][newSecondY];
			
//			System.out.println("OLD: " + twoChars);
			System.out.println("NEW: " + newFirstChar + newSecondChar);
		}
		
		System.out.println();

		
		
		
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
	
	private String findXY(char character) {
		String XY = "";
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if((keyMatrix[i][j]+"").equals(""+character)) {
					XY = (i + "" + j);
				}
			}
		}
		
		return XY;
	}
	
	private void setRepeatedCharactersList() {
		for(int index = 0; index < ALL_CHARS.length(); index++) {
			String character = ALL_CHARS.charAt(index) + "";
			String repeatedChar = character + character;
			String replaceRepeatedChar = character + CHARACTER_TO_REPLACE +character;
			repeatedCharacters.put(repeatedChar, replaceRepeatedChar);
		}
	}
	
	private int increaseAndResetIfMax(int point) {
		point++;
		if(point == 5) {
			return 0;
		}
		return point;
	}
}

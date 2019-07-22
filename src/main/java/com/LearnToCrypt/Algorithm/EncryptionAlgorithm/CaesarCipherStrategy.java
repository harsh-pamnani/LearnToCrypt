package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import com.LearnToCrypt.Algorithm.UserInput;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CaesarCipherStrategy implements IEncryptionAlgorithmStrategy {

    private String result = "";
    private String plaintext = null;
    private ArrayList<String> steps = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
    private static final String ALGORITHM_NAME = "Caesar Cipher";

    @Override
    public String getName() {
        return ALGORITHM_NAME;
    }

    @Override
    public String encode(String key, String plaintext) {
        this.plaintext = plaintext;
        int shift = Integer.parseInt(key);
        char temp = ' ';

        int len = plaintext.length();

        for(int i = 0; i < len; i++){
            if(plaintext.charAt(i) != ' '){
                char c = (char)(plaintext.charAt(i) + shift);

                if ((Character.isLowerCase(plaintext.charAt(i)) && c > 'z')
                        || (Character.isUpperCase(plaintext.charAt(i)) && c > 'Z')) {

                    temp = (char) (plaintext.charAt(i) - (26 - shift));
                    result += temp+"";

                } else{

                    temp = (char)(plaintext.charAt(i) + shift);
                    result += temp+"";

                }
                steps.add(plaintext.charAt(i)+" ----> "+temp+"\n");

            }else {
                result += ' ';
            }
        }
        logger.info("Caesar Cipher: Encoding done.");
        return result;
    }

    @Override
    public String getResult() {
        return plaintext+"\n"+result;
    }

    @Override
    public String getSteps() {
        String stepsString = "";
        int lines = 9;

        if(steps.size() <= lines){
            for (String s : steps) {
                stepsString += s;
            }
        }else {
            for (int i = 0; i < 5; i++) {
                stepsString += steps.get(i);
            }
            stepsString += "......\n";
            for (int i = steps.size()-4; i < steps.size(); i++) {
                stepsString += steps.get(i);
            }
        }
        logger.info("Caesar Cipher: Decoding done.");
        return stepsString;
    }

	@Override
    public String keyPlainTextValidation(UserInput userInput) {
        String formError = null;

        if(userInput.getKey().isEmpty()) {
            formError ="Key can't be empty";
        } else if(!userInput.getKey().matches("^[0-9]*$")) {
            formError = "Enter only numbers in key.";
        } else if(userInput.getPlaintext().isEmpty()) {
            formError = "Plain text can't be empty";
        } else if(!userInput.getPlaintext().matches("[A-Za-z ]+")) {
            formError = "Enter only A-Z in plain text.";
        } else if(Integer.parseInt(userInput.getKey()) > 26){
            formError = "The key must smaller than 26.";
        }

        if (formError == null) {
            logger.info("Caesar Cipher: Key Validated Successfully");
        } else {
            logger.error("Caesar Cipher: Key Validation Error: " + formError);
        }

        return formError;
    }
}


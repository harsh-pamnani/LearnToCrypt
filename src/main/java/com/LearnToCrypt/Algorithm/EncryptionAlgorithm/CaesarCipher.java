package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import java.util.ArrayList;

public class CaesarCipher implements IEncryptionAlgorithm {

    private String result = "";
    private String plaintext = null;
    private ArrayList<String> steps = new ArrayList<>();

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

        return stepsString;
    }
}


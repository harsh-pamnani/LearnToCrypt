package com.LearnToCrypt.Algorithm.Cipher;

public class CaesarCipher implements ICipher {

    private String result = "";
    private String steps = "";

    @Override
    public String encode(String key, String plaintext) {

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
                steps += plaintext.charAt(i)+" -> "+temp+"\n";
            }else {
                result += ' ';
            }
        }
        return result;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getSteps() {
        return steps;
    }
}


package com.LearnToCrypt.HashingAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private static final String ALGORITHM = "MD5";

	public String generateMD5HashValue(String password) {
		String hashedPassword = "";

		try {
			// Took reference from https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
			MessageDigest md5Instance = MessageDigest.getInstance(ALGORITHM);
			md5Instance.update(password.getBytes());
			byte[] mdBytes = md5Instance.digest();
			StringBuilder stringBuilder = new StringBuilder();
			
			for (int i = 0; i < mdBytes.length; i++) {
				stringBuilder.append(Integer.toString((mdBytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			hashedPassword = stringBuilder.toString();

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error occured in getting the algorithm.");
			System.out.println("Error : " + e.getMessage());
		}

		return hashedPassword;
	}
}

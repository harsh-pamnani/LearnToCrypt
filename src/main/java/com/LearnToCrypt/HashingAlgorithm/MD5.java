package com.LearnToCrypt.HashingAlgorithm;

import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private static final String ALGORITHM = "MD5";
	private static final Logger logger = LogManager.getLogger(MD5.class);


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
			System.out.println("Error occurred in getting the algorithm.");
			System.out.println("Error : " + e.getMessage());
			logger.error("Error occurred in getting the algorithm.",e);
		}

		return hashedPassword;
	}
}

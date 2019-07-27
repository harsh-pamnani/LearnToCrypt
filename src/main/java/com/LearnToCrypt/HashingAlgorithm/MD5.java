package com.LearnToCrypt.HashingAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MD5 implements IHash {

	private static final String ALGORITHM = "MD5";
    private static final Logger logger = LogManager.getLogger(MD5.class);

	@Override
	public String generateHashValue(String password) {
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
			logger.error("Error in generating the hash value of password using MD5 algorithm.", e);
		}

		return hashedPassword;
	}
}

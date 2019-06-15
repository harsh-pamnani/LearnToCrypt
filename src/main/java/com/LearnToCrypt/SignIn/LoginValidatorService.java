package com.LearnToCrypt.SignIn;

import org.springframework.stereotype.Service;

@Service
public class LoginValidatorService {
	
	public boolean validateLoginCredentials(String email, String password) {
		// Password validations goes here
		if (email.equals("harsh") && password.equals("harsh")) {
			return true;
		} else {
			return false;
		}
	}
}

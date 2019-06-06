package com.example.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoginValidatorService {
	
	public boolean validateLoginCredentials(String email, String password) {
		// Password validations goes here
		if (email.equals("harsh@gmail.com") && password.equals("Hars@123")) {
			return true;
		} else {
			return false;
		}
	}
}

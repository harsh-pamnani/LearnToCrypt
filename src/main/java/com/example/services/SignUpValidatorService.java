package com.example.services;

import org.springframework.stereotype.Service;

@Service
public class SignUpValidatorService {
	
	public String validateSignUpForm(String name, String email, String password, String confirmPassword) {
		// Password validations goes here
		if (name.equals("")) {
			return "Empty Name";
		} else if (!email.equals("harsh")) {
			return "Invalid email";
		} else if (password.equals("")) {
			return "Empty Password";
		} else if (!confirmPassword.equals(password)) {
			return "Wrong Confirm Password";
		} else {
			return "";
		}	
	}
}

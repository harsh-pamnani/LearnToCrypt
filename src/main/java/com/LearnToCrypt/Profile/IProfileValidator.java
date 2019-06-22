package com.LearnToCrypt.Profile;

public interface IProfileValidator {
	String isNameValid(String name);
	String isPasswordValid(String Password, String confirmPassword);
}

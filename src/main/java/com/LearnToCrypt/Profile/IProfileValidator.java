package com.LearnToCrypt.Profile;

import javax.xml.bind.ValidationException;

public interface IProfileValidator {
	void validateName(String name) throws ValidationException;
	void validatePassword(String Password, String confirmPassword) throws ValidationException;
}

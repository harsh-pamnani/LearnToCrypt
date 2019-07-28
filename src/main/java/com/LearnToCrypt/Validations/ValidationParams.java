package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidationParams implements IValidationParams {

	private static final Logger logger = LogManager.getLogger(ValidationParams.class);
	private String name;
	private String password;
	private String confirmPassword;
	private String email;
	private String role;

	public ValidationParams() {
		logger.info("Creating Validation Parameters");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getConfirmPassword() {
		return confirmPassword;
	}

	@Override
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}
}

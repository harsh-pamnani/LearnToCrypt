package com.LearnToCrypt.BusinessModels;

public class User {
	private String email;
	private String name;
	private String password;
	private String role;
	private String progress;
	private String UserClass;

	public String getUserClass() {
		return UserClass;
	}

	public void setUserClass(String userClass) {
		UserClass = userClass;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

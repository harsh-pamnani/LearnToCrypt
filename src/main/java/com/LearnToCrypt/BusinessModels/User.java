package com.LearnToCrypt.BusinessModels;

public class User implements IUser {
	private String email;
	private String name;
	private String password;
	private String role;
	private String progress;
	private String UserClass;

	@Override
	public String getUserClass() {
		return UserClass;
	}

	@Override
	public void setUserClass(String userClass) {
		UserClass = userClass;
	}

	@Override
	public String getProgress() {
		return progress;
	}

	@Override
	public void setProgress(String progress) {
		this.progress = progress;
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
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}
}

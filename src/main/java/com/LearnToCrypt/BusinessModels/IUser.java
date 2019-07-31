package com.LearnToCrypt.BusinessModels;

public interface IUser {
	String getUserClass();

	void setUserClass(String userClass);

	String getProgress();

	void setProgress(String progress);

	String getEmail();

	void setEmail(String email);

	String getName();

	void setName(String name);

	String getPassword();

	void setPassword(String password);

	String getRole();

	void setRole(String role);
}

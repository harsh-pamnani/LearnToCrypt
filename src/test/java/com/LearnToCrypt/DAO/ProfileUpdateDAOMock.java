package com.LearnToCrypt.DAO;

import java.util.HashMap;
import java.util.Map;

public class ProfileUpdateDAOMock implements IPasswordUpdaterDAO, INameSetterDAO {

	private UserDAOFactoryMock userDAOFactoryMock;
	private Map<String,String> tokens;

	public ProfileUpdateDAOMock() {
		this.userDAOFactoryMock = new UserDAOFactoryMock();
		this.tokens = new HashMap<>();
		tokens.put("fbaffcc1-99c3-4082-9e40-a3300571bbb6","harshpam1993@gmail.com");
		tokens.put("fa735525-1bf4-4d3c-a8ff-70908ae10c31","aman.arya@yopmail.com");
	}

	@Override
	public void setPassword(String email, String password) {
		userDAOFactoryMock.getUser(email).setPassword(password);
	}

	@Override
	public void setResetToken(String email, String token) {
		tokens.replace(token,email);
	}

	@Override
	public String getEmailFromToken(String token) {
		return tokens.get(token);
	}

	@Override
	public void setName(String email, String name) {
		userDAOFactoryMock.getUser(email).setName(name);
	}
}

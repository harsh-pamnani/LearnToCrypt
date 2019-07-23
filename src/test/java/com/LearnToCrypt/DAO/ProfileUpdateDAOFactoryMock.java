package com.LearnToCrypt.DAO;

import java.util.HashMap;
import java.util.Map;

public class ProfileUpdateDAOFactoryMock implements IPasswordUpdaterDAO {

	private UserDAOFactoryMock userDAOFactoryMock;
	private Map<String,String> tokens;

	public ProfileUpdateDAOFactoryMock() {
		this.userDAOFactoryMock = new UserDAOFactoryMock();
		this.tokens = new HashMap<>();
		tokens.put("harshpam1993@gmail.com","fbaffcc1-99c3-4082-9e40-a3300571bbb6");
		tokens.put("aman.arya@yopmail.com","fa735525-1bf4-4d3c-a8ff-70908ae10c31");
	}

	@Override
	public void setPassword(String email, String password) {
		userDAOFactoryMock.getUser(email).setPassword(password);
	}

	@Override
	public void setResetToken(String email, String token) {
		tokens.replace(email,token);
	}

	@Override
	public String getEmailFromToken(String token) {
		return "fbaffcc1-99c3-4082-9e40-a3300571bbb6";
	}
}

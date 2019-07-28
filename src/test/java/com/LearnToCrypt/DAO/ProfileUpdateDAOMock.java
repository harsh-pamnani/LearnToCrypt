package com.LearnToCrypt.DAO;

import com.LearnToCrypt.HashingAlgorithm.IHash;
import com.LearnToCrypt.HashingAlgorithm.MD5;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProfileUpdateDAOMock implements IPasswordUpdaterDAO, INameSetterDAO {

	private IUserDAO userDAOFactoryMock;
	private Map<String,String> tokens;
	private IHash hash;

	public ProfileUpdateDAOMock(IUserDAO userDAO) {
		this.userDAOFactoryMock = userDAO;
		this.tokens = new HashMap<>();
		tokens.put("fbaffcc1-99c3-4082-9e40-a3300571bbb6","harshpam1993@gmail.com");
		tokens.put("fa735525-1bf4-4d3c-a8ff-70908ae10c31","aman.arya@yopmail.com");
		tokens.put("fa735525-1bf4-4d3c-a8ff-70908ae10c32","update@pass.com");
		hash = new MD5();
	}

	@Override
	public void setPassword(String email, String password) {
		try {
			userDAOFactoryMock.getUser(email).setPassword(hash.generateHashValue(password));
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		try {
			userDAOFactoryMock.getUser(email).setName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

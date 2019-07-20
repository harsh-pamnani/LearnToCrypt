package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.User;

public interface IUserDAO {
	
	public void createUser(User user);
	
	public boolean isUserValid(User user);
	
	public boolean isUserRegistered(User user);
	
	public String getUserName(String email);

	public User getUser(String email);
	
	public String getUserRole(String email);

	public String[] getProgress(String email);

	public void updateProgress(String email, String newProgress);

	public String getUserClass(String email);
	
	public boolean deleteUser(String email);
}

package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.IUser;

public interface IUserDAO {
	
	public void createUser(IUser user);
	
	public boolean isUserValid(IUser user);
	
	public boolean isUserRegistered(IUser user);
	
	public String getUserName(String email);

	public IUser getUser(String email);
	
	public String getUserRole(String email);

	public String[] getProgress(String email);

	public void updateProgress(String email, String newProgress);
}

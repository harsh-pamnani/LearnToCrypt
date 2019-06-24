package com.LearnToCrypt.DAO;

import java.util.ArrayList;
import java.util.List;

import com.LearnToCrypt.BusinessModels.User;

public class UserDAOFactoryMock implements IUserDAO {

	List<User> users;

	public UserDAOFactoryMock() {
		users = new ArrayList<User>();
		addUsers();
	}

	private void addUsers() {
		User user1 = new User();
		user1.setEmail("rob@gmail.com");
		user1.setName("Robert Hawkey");
		user1.setPassword("Rob@1234");
		user1.setRole("Instructor");

		User user2 = new User();
		user2.setEmail("shelley@gmail.com");
		user2.setName("Shelley Dhillan");
		user2.setPassword("Dhillan@999");
		user2.setRole("Student");

		User user3 = new User();
		user3.setEmail("milly@gmail.com");
		user3.setName("Milly Duke");
		user3.setPassword("Milly@9876");
		user3.setRole("Student");
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}

	@Override
	public void createUser(User user) {
		users.add(user);
	}

	@Override
	public boolean isUserValid(User user) {	
		boolean isValid = false;
		
		for (User u : users) {
			if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
				isValid = true;
				break;
			}
		}
		
		return isValid;
	}

	@Override
	public boolean isUserRegistered(User user) {
		boolean isRegistered = false;

		for (User u : users) {
			if (u.getEmail().equals(user.getEmail())) {
				isRegistered = true;
				break;
			}
		}

		return isRegistered;
	}

	@Override
	public String getUserName(String email) {
		String username = "";
		
		for (User u : users) {
			if (u.getEmail().equals(email)) {
				username = u.getName();
				break;
			}
		}
		
		return username;
	}

	@Override
	public User getUser(String email) {
		return users.get(0);
	}

}
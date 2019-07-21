package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProfileUpdaterTest {

	BusinessModelAbstractFactory businessModelAbstractFactory;
	IDAOAbstractFactory abstractFactory;
	IUserDAO userDAO;
	User user;
	MD5 md5;
	IUserNameChanger userNameChanger;
	IPasswordChanger passwordChanger;
	IUserProfileBridge profileBridge;

	public ProfileUpdaterTest() {
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
		userNameChanger = new ProfileUpdater();
		passwordChanger = new ProfileUpdater();
		abstractFactory = new DAOAbstractFactory();
		userDAO = abstractFactory.createUserDAO();
		user = businessModelAbstractFactory.createUser();
		user.setEmail("update@profile.com");
		user.setPassword("ProfileUpdate@123");
		user.setName("Profile Update");
		user.setRole("Student");
		md5 = new MD5();
		if(!userDAO.isUserRegistered(user)) {
			userDAO.createUser(user);
		}
		profileBridge = new UserProfile(user.getEmail());
	}

	@Test
	public void testChangeName() {
		userNameChanger.changeName(user.getEmail(), "Profile");
		IUserProfileBridge testProfile = new UserProfile(user.getEmail());
		userNameChanger.changeName(user.getEmail(), user.getName());
		assertNotEquals(profileBridge.getUser(), testProfile.getUser());
		assertEquals("Profile", testProfile.getUserName());
	}

	@Test
	public void testChangePassword() {
		passwordChanger.changePassword(user.getEmail(), "NewPass@123");
		IUserProfileBridge testProfile = new UserProfile(user.getEmail());
		passwordChanger.changePassword(user.getEmail(), user.getPassword());
		assertNotEquals(profileBridge.getUser(), testProfile.getUser());
		assertEquals(md5.generateHashValue("NewPass@123"), testProfile.getUser().getPassword());
	}
}

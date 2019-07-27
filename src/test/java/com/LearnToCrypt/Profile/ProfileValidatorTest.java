package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.User;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ProfileValidatorTest {
	IUserProfileBridge profileBridge;
	IProfileValidator profileValidator;
	User user;

	public ProfileValidatorTest() {
		profileBridge = new UserProfileMock();
		user = profileBridge.getUser();
		user.setPassword("Profile@123");
		profileValidator = new ProfileValidator(profileBridge);
	}

	@Test
	public void testIsNameValid() {
		assertNull(profileValidator.validateName(user.getName()));
		assertNotNull(profileValidator.validateName("12347"));
	}

//	@Test
//	public void testIsPasswordValid() {
//		assertNull(profileValidator.validatePassword(user.getPassword(), user.getPassword()));
//		assertNotNull(profileValidator.validatePassword("Invalid", "Invalid"));
//	}
}

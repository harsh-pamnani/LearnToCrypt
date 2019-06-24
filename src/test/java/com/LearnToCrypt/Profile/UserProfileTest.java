package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserProfileTest {

    IUserProfileBridge profile;
    BusinessModelAbstractFactory businessModelAbstractFactory;
    IDAOAbstractFactory abstractFactory;
    IUserDAO userDAO;
    User user;

    public UserProfileTest() {
        businessModelAbstractFactory = new BusinessModelAbstractFactory();
        abstractFactory = new DAOAbstractFactory();
        userDAO = abstractFactory.createUserDAO();
        user = businessModelAbstractFactory.createUser();
        user.setEmail("test@profile.com");
        user.setPassword("Profile@123");
        user.setName("Profile");
        user.setRole("Student");
        if(!userDAO.isUserRegistered(user)) {
            userDAO.createUser(user);
        }
        profile = new UserProfile(user.getEmail());
    }

    @Test
    public void testGetUserName() {
        assertEquals(user.getName(), profile.getUserName());
    }

    @Test
    public void testGetEmail() {
        assertEquals(user.getEmail(), profile.getEmail());
    }

    @Test
    public void testGetRole() {
        assertEquals(user.getRole(), profile.getRole());
    }
}

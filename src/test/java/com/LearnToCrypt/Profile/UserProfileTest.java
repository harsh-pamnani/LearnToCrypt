package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UserProfileTest {

    private static final Logger logger = LogManager.getLogger(UserProfileTest.class);
    private IUserProfileBridge profile;
    private BusinessModelAbstractFactory businessModelAbstractFactory;
    private IDAOAbstractFactory abstractFactory;
    private IUserDAO userDAO;
    private User user;

    public UserProfileTest() {
        businessModelAbstractFactory = new BusinessModelAbstractFactory();
        abstractFactory = new DAOAbstractFactoryMock();
        userDAO = abstractFactory.createUserDAO();
        user = businessModelAbstractFactory.createUser();
        user.setEmail("test@profile.com");
        user.setName("Profile");
        user.setRole("Student");
        if(!userDAO.isUserRegistered(user.getEmail())) {
            userDAO.createUser(user);
        }
        try {
            profile = new UserProfile(user.getEmail(), abstractFactory);
        } catch (SQLException e) {
            // Using mock objects. No connection to database and hence, no exception should be raised.
            logger.error("Test should not connect to database. Exception: " + e.getMessage());
        }
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

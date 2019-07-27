package com.LearnToCrypt.SignIn;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.UserDAOFactoryMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ValidateUserCredentialsTest {
    UserDAOFactoryMock userDAOFactoryMock;
    BusinessModelAbstractFactory businessModelAbstractFactory;
    User user;

    public ValidateUserCredentialsTest() {
        userDAOFactoryMock = new UserDAOFactoryMock();
        businessModelAbstractFactory = new BusinessModelAbstractFactory();
        user = businessModelAbstractFactory.createUser();
    }

    @Test
    public void testValidateCredentials() {
        user.setEmail("milly@gmail.com");
        user.setName("Milly Duke");
        user.setPassword("Milly@9876");
        user.setRole("Student");
        assertTrue(userDAOFactoryMock.isUserValid(user));

        user.setEmail("notregistered@fake.com");
        assertFalse(userDAOFactoryMock.isUserValid(user));
    }
}

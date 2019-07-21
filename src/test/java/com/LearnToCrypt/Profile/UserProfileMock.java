package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.HashingAlgorithm.MD5;

public class UserProfileMock implements IUserProfileBridge {

    BusinessModelAbstractFactory businessModelAbstractFactory;
    IDAOAbstractFactory abstractFactory;
    IUserDAO userDAO;
    User user;
    MD5 md5;

    public UserProfileMock() {
        businessModelAbstractFactory = new BusinessModelAbstractFactory();
        abstractFactory = new DAOAbstractFactory();
        userDAO = abstractFactory.createUserDAO();
        user = businessModelAbstractFactory.createUser();
        md5 = new MD5();
        user.setEmail("test@profile.com");
        user.setPassword(md5.generateHashValue("Profile@123"));
        user.setName("Profile");
        user.setRole("Student");
    }

    @Override
    public String getUserName() {
        return user.getName();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getRole() {
        return user.getRole();
    }

    @Override
    public User getUser() {
        return user;
    }
}

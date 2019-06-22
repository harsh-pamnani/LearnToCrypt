package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

public class UserProfile implements IUserProfileBridge {

    IDAOAbstractFactory abstractFactory;
    IUserDAO userDAO;
    User user;

    UserProfile(String email) {
        abstractFactory = new DAOAbstractFactory();
        userDAO = abstractFactory.createUserDAO();
        user = new User();
        user = userDAO.getUser(email);
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
}

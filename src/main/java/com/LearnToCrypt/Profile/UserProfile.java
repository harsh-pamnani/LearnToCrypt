package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.IUser;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

import java.sql.SQLException;

public class UserProfile implements IUserProfileBridge {

    IUser user;

    UserProfile(String email, IDAOAbstractFactory abstractFactory) throws SQLException {
        IUserDAO userDAO = abstractFactory.createUserDAO();
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

    @Override
    public String getHashedPassword() {
        return user.getPassword();
    }

}

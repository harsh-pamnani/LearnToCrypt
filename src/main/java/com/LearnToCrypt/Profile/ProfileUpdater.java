package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.INameSetterDAO;
import com.LearnToCrypt.DAO.IPasswordUpdaterDAO;

public class ProfileUpdater implements IPasswordChanger, IUserNameChanger {

    private INameSetterDAO nameSetterDAO;
    private IPasswordUpdaterDAO passwordUpdaterDAO;

    public ProfileUpdater(INameSetterDAO nameSetterDAO, IPasswordUpdaterDAO passwordUpdaterDAO) {
        this.nameSetterDAO = nameSetterDAO;
        this.passwordUpdaterDAO = passwordUpdaterDAO;
    }

    @Override
    public void changePassword(String email, String newPassword) {
        passwordUpdaterDAO.setPassword(email, newPassword);
    }

    @Override
    public void changeName(String email, String newName) {
        nameSetterDAO.setName(email, newName);
    }
}

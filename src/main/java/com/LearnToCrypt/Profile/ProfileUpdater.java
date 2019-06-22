package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.INameSetterDAO;
import com.LearnToCrypt.DAO.IPasswordSetterDAO;

public class ProfileUpdater implements IPasswordChanger, IUserNameChanger {
    @Override
    public void changePassword(String email, String newPassword) {
        IDAOAbstractFactory abstractFactory = new DAOAbstractFactory();
        IPasswordSetterDAO passwordSetterDAO = abstractFactory.createPasswordSetterDAO();
        passwordSetterDAO.setPassword(email, newPassword);
    }

    @Override
    public void changeName(String email, String newName) {
        IDAOAbstractFactory abstractFactory = new DAOAbstractFactory();
        INameSetterDAO nameSetterDAO = abstractFactory.createNameSetterDAO();
        nameSetterDAO.setName(email, newName);
    }
}

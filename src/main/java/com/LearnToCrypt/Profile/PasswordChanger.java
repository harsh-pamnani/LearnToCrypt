package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IPasswordSetterDAO;

public class PasswordChanger implements IPasswordChanger {
    @Override
    public void changePassword(String email, String newPassword) {
        IDAOAbstractFactory abstractFactory = new DAOAbstractFactory();
        IPasswordSetterDAO passwordSetterDAO = abstractFactory.createPasswordSetterDAO();
        passwordSetterDAO.setPassword(email, newPassword);
    }
}

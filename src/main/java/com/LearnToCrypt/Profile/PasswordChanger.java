package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IPasswordUpdaterDAO;

import java.sql.SQLException;

public class PasswordChanger implements IPasswordChanger {

	private IPasswordUpdaterDAO passwordUpdaterDAO;

	public PasswordChanger(IDAOAbstractFactory factory) {
		passwordUpdaterDAO = factory.createPasswordSetterDAO();
	}

	@Override
	public void changePassword(String email, String newPassword) throws SQLException {
		passwordUpdaterDAO.setPassword(email, newPassword);
	}

	@Override
	public String getEmailFromToken(String token) throws SQLException {
		return getEmailFromToken(token);
	}
}

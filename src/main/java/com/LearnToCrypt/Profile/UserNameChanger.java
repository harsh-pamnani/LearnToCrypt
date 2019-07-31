package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.INameSetterDAO;

import java.sql.SQLException;

public class UserNameChanger implements IUserNameChanger {

	private INameSetterDAO nameSetterDAO;

	public UserNameChanger(IDAOAbstractFactory factory) {
		nameSetterDAO = factory.createNameSetterDAO();
	}

	@Override
	public void changeName(String email, String newName) throws SQLException {
		nameSetterDAO.setName(email, newName);
	}
}

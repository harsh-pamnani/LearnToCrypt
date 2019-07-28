package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.SignIn.AuthenticationManager;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public interface IProfileCreator {
	IUserProfileBridge getProfile(AuthenticationManager authenticationManager,
								  HttpSession httpSession,
								  IDAOAbstractFactory abstractFactory) throws AuthenticationException, SQLException;
}

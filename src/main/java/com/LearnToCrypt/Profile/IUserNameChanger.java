package com.LearnToCrypt.Profile;

import java.sql.SQLException;

public interface IUserNameChanger {
	void changeName(String email, String newName) throws SQLException;
}

package com.LearnToCrypt.Profile;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

public interface IUpdateProfile {
	void update(IUserProfileBridge profile, String newName, String newPassword, String confirmPassword) throws ValidationException, SQLException;
}

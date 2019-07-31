package com.LearnToCrypt.Profile;

import java.sql.SQLException;

public interface IPasswordChanger {
    void changePassword(String email, String newPassword) throws SQLException;
    String getEmailFromToken(String token) throws SQLException;
}

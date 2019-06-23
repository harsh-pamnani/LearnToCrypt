package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.User;

public interface IPasswordUpdaterDAO {
    void setPassword(String email, String password);

    void setResetToken(String email, String token);

    String getEmailFromToken(String token);
}

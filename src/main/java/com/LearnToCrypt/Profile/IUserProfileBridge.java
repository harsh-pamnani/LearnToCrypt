package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.User;

public interface IUserProfileBridge {
    String getUserName();

    String getEmail();

    String getRole();

    User getUser();
}

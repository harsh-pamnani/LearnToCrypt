package com.LearnToCrypt.Profile;

import com.LearnToCrypt.BusinessModels.IUser;

public interface IUserProfileBridge {
    String getUserName();

    String getEmail();

    String getRole();

    IUser getUser();
}

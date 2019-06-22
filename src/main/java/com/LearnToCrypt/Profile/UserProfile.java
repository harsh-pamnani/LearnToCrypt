package com.LearnToCrypt.Profile;

public class UserProfile implements IProfile {
    @Override
    public String getUserName() {
        return "Test";
    }

    @Override
    public String getEmail() {
        return "Test@test.com";
    }

    @Override
    public String getRole() {
        return "Student";
    }
}

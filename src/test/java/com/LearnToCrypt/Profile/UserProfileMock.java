package com.LearnToCrypt.Profile;

public class UserProfileMock implements IUserProfileBridge {

    private String name;
    private String email;
    private String role;

    public UserProfileMock(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getRole() {
        return role;
    }
}

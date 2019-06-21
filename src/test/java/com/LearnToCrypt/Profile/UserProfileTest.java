package com.LearnToCrypt.Profile;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserProfileTest {

    IProfile profile;

    public UserProfileTest() {
        //TODO: Test Setup
        profile = new UserProfileMock("Hali Fax", "Halifax@ns.com", "Student");
    }

    @Test
    public void testGetUserName() {
        assertEquals("Hali Fax", profile.getUserName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("Halifax@ns.com", profile.getEmail());
    }

    @Test
    public void testGetRole() {
        assertEquals("Student", profile.getRole());
    }
}

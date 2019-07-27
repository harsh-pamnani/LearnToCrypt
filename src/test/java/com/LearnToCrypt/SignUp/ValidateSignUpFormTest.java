package com.LearnToCrypt.SignUp;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ValidateSignUpFormTest {
    ValidateSignUpForm validateSignUpForm;
    BusinessModelAbstractFactory businessModelAbstractFactory;
    User user;

    public ValidateSignUpFormTest() {
        validateSignUpForm = new ValidateSignUpForm();
        businessModelAbstractFactory = new BusinessModelAbstractFactory();
        user = businessModelAbstractFactory.createUser();
        user.setEmail("harsh@gmail.com");
        user.setPassword("Harsh@123");
        user.setName("Harsh Pamnani");
        user.setRole("Student");
    }

    @Test
    public void testValidateFormDetails() {
        user.setEmail("harsh@gmail.");
        try {
            validateSignUpForm.validateFormDetails(user, "Harsh@123");
        } catch (SignUpFailureException e) {
            assertEquals("Email id is not valid.", e.getMessage());
            assertNotEquals("Email not valid.", e.getMessage());
        }

        user.setEmail("harsh@gmail.com");
        user.setName("Harsh 123");
        try {
            validateSignUpForm.validateFormDetails(user, "Harsh@123");
        } catch (SignUpFailureException e) {
            assertEquals("Name can not contain any digits or special characters.", e.getMessage());
            assertNotEquals("Name is invalid.", e.getMessage());
        }


        user.setName("Harsh Pamnani");
        user.setPassword("Hphphp@123");
        try {
            validateSignUpForm.validateFormDetails(user, "Hphphp@456");
        } catch (SignUpFailureException e) {
            assertEquals("Confirm Password doesn't match.", e.getMessage());
            assertNotEquals("Password not valid.", e.getMessage());
        }

        user.setPassword("HARSH@123");
        try {
            validateSignUpForm.validateFormDetails(user, "HARSH@123");
        } catch (SignUpFailureException e) {
            assertEquals("Password must contain at least 1 lowercase letter.", e.getMessage());
            assertNotEquals("Password not valid.", e.getMessage());
        }

        user.setPassword("Harsh1234");
        try {
            validateSignUpForm.validateFormDetails(user, "Harsh1234");
        } catch (SignUpFailureException e) {
            assertEquals("Password must contain at least 1 special charachter.", e.getMessage());
            assertNotEquals("Password not valid.", e.getMessage());
        }

        user.setPassword("harsh@123");
        try {
            validateSignUpForm.validateFormDetails(user, "harsh@123");
        } catch (SignUpFailureException e) {
            assertEquals("Password must contain at least 1 uppercase letter.", e.getMessage());
            assertNotEquals("Password not valid.", e.getMessage());
        }

        user.setPassword("Harsh@123");
        user.setRole("Admin");
        try {
            validateSignUpForm.validateFormDetails(user, "Harsh@123");
        } catch (SignUpFailureException e) {
            assertEquals("Role can not be empty.", e.getMessage());
            assertNotEquals("Password not valid.", e.getMessage());
        }
    }
}

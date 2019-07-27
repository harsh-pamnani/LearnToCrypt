package com.LearnToCrypt.DAO;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SignUpValidationRulesDAOTest {

    SignUpValidationRulesDAOFactoryMock signUpDAOMock;

    public SignUpValidationRulesDAOTest() {
        signUpDAOMock = new SignUpValidationRulesDAOFactoryMock();
    }

    @Test
    public void testGetRulesValue() {
        assertEquals("Yes", signUpDAOMock.getRulesValue("ConfirmPasswordValidation"));
        assertNotEquals("No", signUpDAOMock.getRulesValue("ConfirmPasswordValidation"));

        assertEquals("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", signUpDAOMock.getRulesValue("EmailValidation"));
        assertNotEquals("[A-Z0-9]", signUpDAOMock.getRulesValue("EmailValidation"));

        assertEquals("[a-zA-Z ]+", signUpDAOMock.getRulesValue("NameCharactersValidation"));
        assertNotEquals("[a-z ]+", signUpDAOMock.getRulesValue("NameCharactersValidation"));

        assertEquals("8", signUpDAOMock.getRulesValue("PasswordLengthValidation"));
        assertNotEquals("10", signUpDAOMock.getRulesValue("PasswordLengthValidation"));

        assertEquals(".*[a-z].*", signUpDAOMock.getRulesValue("PasswordLowerCaseValidation"));
        assertNotEquals(".*[a].*", signUpDAOMock.getRulesValue("PasswordLowerCaseValidation"));

        assertEquals(".*[!@#$%^&*()].*", signUpDAOMock.getRulesValue("PasswordSpecialCharValidation"));
        assertNotEquals(".*[&*()].*", signUpDAOMock.getRulesValue("PasswordSpecialCharValidation"));

        assertEquals(".*[A-Z].*", signUpDAOMock.getRulesValue("PasswordUpperCaseValidation"));
        assertNotEquals(".*[A].*", signUpDAOMock.getRulesValue("PasswordUpperCaseValidation"));

        assertEquals("Student Instructor", signUpDAOMock.getRulesValue("RoleValidation"));
        assertNotEquals("Student Admin", signUpDAOMock.getRulesValue("RoleValidation"));
    }

    @Test
    public void testGetRules() {
        assertTrue(signUpDAOMock.getRules() instanceof List<?>);
        assertFalse(signUpDAOMock.getRules() instanceof Map<?, ?>);
    }
}

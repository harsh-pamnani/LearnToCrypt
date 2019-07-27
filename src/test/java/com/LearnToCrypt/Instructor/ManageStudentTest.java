package com.LearnToCrypt.Instructor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ManageStudentTest {
    ManageStudent manageStudent;

    public ManageStudentTest() {
        manageStudent = new ManageStudent();
    }

    @Test
    public void testDeleteStudent() {
        try {
            manageStudent.deleteStudent("harsh@gmail.com", "abcd@fake.com");
        } catch (DeleteStudentException e) {
            assertEquals("Some error occurred in deleting the user. Please try again.", e.getMessage());
            assertNotEquals("Error occurred", e.getMessage());
        }

        try {
            manageStudent.deleteStudent("harsh@gmail.com", "harsh@gmail.com");
        } catch (DeleteStudentException e) {
            assertEquals("Some error occurred in deleting the user. Please try again.", e.getMessage());
            assertNotEquals("Error occurred", e.getMessage());
        }
    }
}

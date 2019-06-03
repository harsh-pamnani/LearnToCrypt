package com.example.LearnToCrypt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTests {

    @Test
    public void getStudentNameTest() {
        Student s1 = new Student("harsh", "hr340096@dal.ca");
        assertEquals("getStudentNameTest", "harsh", s1.getName());
    }

    @Test
    public void getStudentEmailTest() {
        Student s2 = new Student("tony", "tony123@dal.ca");
        assertEquals("getStudentEmailTest", "tony123@dal.ca", s2.getEmail());
    }

    @Test
    public void newTest() {
        Student s3 = new Student("aman", "abbbcdd@dal.ca");
        assertEquals("getStudentEmailTest", "abbbcdd@dal.ca", s3.getEmail());
    }

    @Test
    public void newTest2() {
        Student s3 = new Student("aman", "fail@dal.ca");
        assertEquals("getStudentNameTest", "aman", s3.getName());
    }

    @Test
    public void newTest3() {
        Student s3 = new Student("arya", "arya@dal.ca");
        assertEquals("getStudentNameTest", "arya", s3.getName());
    }
}
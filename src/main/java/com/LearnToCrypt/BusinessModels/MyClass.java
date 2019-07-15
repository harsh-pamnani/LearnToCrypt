package com.LearnToCrypt.BusinessModels;

import java.util.ArrayList;

public class MyClass {
    private String className;
    private String instructorID;
    private String alg;
    private ArrayList<User> students;

    public MyClass(String className, String instructorID, String alg) {
        this.className = className;
        this.instructorID = instructorID;
        this.alg = alg;
        this.students = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public String[] getAlgList() {
        return alg.split(",");
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public ArrayList<User> getStudents() {
        return students;
    }

    public void addStudents(User student) {
        this.students.add(student);
    }
}

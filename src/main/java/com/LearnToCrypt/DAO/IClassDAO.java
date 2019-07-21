package com.LearnToCrypt.DAO;

import java.util.ArrayList;
import java.util.List;

import com.LearnToCrypt.BusinessModels.MyClass;

public interface IClassDAO {
    public void createClass(MyClass myClass);
    public List<MyClass> getClass(String instructorID);
    public void deleteStudentFromClass(String emailID);
    public void addStudentToClass(ArrayList<String> studentList, String className);
    public void deleteClass(String className);
}

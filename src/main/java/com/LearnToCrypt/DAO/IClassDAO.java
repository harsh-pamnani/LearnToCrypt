package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.MyClass;

import java.util.ArrayList;
import java.util.List;

public interface IClassDAO {
    public void createClass(MyClass myClass);
    public List getClass(String instructorID);
    public void deleteStudentFromClass(String emailID);
    public void addStudentToClass(ArrayList<String> studentList, String className);
}

package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.MyClass;

import java.util.List;

public interface IClassDAO {
    public void createClass(MyClass myClass);
    public List getClass(String instructorID);
}

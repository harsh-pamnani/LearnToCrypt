package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.MyClass;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DatabaseConnection.DBConnection;
import com.LearnToCrypt.HashingAlgorithm.MD5;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements IClassDAO{
    private DBConnection dbConnectionInstance = null;
    private Connection dbConnection = null;
    private PreparedStatement statement;

    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

    public ClassDAO() {
        dbConnectionInstance = DBConnection.instance();
    }

    @Override
    public void createClass(MyClass myClass) {
        //TODO: String query = "CALL create_class();";
        String query = "call CSCI5308_7_TEST.create_class('"+myClass.getClassName()+"', '"+myClass.getInstructorID()+"', '"+myClass.getAlg()+"');";
        try {
            dbConnection = dbConnectionInstance.getConnection();

            statement = dbConnection.prepareStatement(query);
            statement.executeQuery();

        } catch (SQLException e) {
            logger.error("Error in creating a new user.", e);
        } finally {
            dbConnectionInstance.closeConnection();
        }
    }

    @Override
    public ArrayList getClass(String instructorID) {
        String query1 = "call CSCI5308_7_TEST.get_class('"+instructorID+"');";
        ArrayList<MyClass> myClasses = new ArrayList<>();
        try {
            dbConnection = dbConnectionInstance.getConnection();

            statement = dbConnection.prepareStatement(query1);
            ResultSet classes = statement.executeQuery();

            while (classes.next()) {
                MyClass myClass = new MyClass(classes.getString(1), classes.getString(2), classes.getString(3));
                String query2 = "call CSCI5308_7_TEST.get_class_student('"+classes.getString(1)+"');";
                statement = dbConnection.prepareStatement(query2);
                ResultSet students = statement.executeQuery();
                while (students.next()){
                    User student = new User();
                    student.setEmail(students.getString(1));
                    student.setName(students.getString(2));
                    int p = 0;
                    if(students.getString(5) != null){
                        p = students.getString(5).split(",").length;
                    }
                    student.setProgress(p+"/"+myClass.getAlg().split(",").length);
                    System.out.println();
                    myClass.addStudents(student);
                }
                myClasses.add(myClass);
            }

        } catch (SQLException e) {
            logger.error("Error in creating a new user.", e);
        } finally {
            dbConnectionInstance.closeConnection();
        }
        return myClasses;
    }

    @Override
    public void deleteStudentFromClass(String emailID) {
        System.out.println("Student "+emailID+" has been kicked out");
    }
}

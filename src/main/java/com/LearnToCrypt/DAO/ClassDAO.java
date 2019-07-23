package com.LearnToCrypt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.MyClass;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DatabaseConnection.DBConnection;

public class ClassDAO implements IClassDAO {
	private DBConnection dbConnectionInstance = null;
	private Connection dbConnection = null;
	private PreparedStatement statement;

	private static final Logger logger = LogManager.getLogger(ClassDAO.class);

	public ClassDAO() {
		dbConnectionInstance = DBConnection.instance();
	}

	@Override
	public void createClass(MyClass myClass) {
		String query = "call create_class('" + myClass.getClassName() + "', '"
				+ myClass.getInstructorID() + "', '" + myClass.getAlg() + "');";
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
	public List<MyClass> getClass(String instructorID) {
		String query1 = "call get_class('" + instructorID + "');";
		ArrayList<MyClass> myClasses = new ArrayList<>();
		try {
			dbConnection = dbConnectionInstance.getConnection();

			statement = dbConnection.prepareStatement(query1);
			ResultSet classes = statement.executeQuery();

			while (classes.next()) {
				MyClass myClass = new MyClass(classes.getString(1), classes.getString(2), classes.getString(3));
				String query2 = "call get_class_student('" + classes.getString(1) + "');";
				statement = dbConnection.prepareStatement(query2);
				ResultSet students = statement.executeQuery();
				while (students.next()) {
					User student = new User();
					student.setEmail(students.getString(1));
					student.setName(students.getString(2));
					int p = 0;
					if (students.getString(5) != null) {
						p = students.getString(5).split(",").length;
					}
					student.setProgress(p + "/" + myClass.getAlg().split(",").length);
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
		String query = "call remove_student_from_class('" + emailID + "');";
		try {
			dbConnection = dbConnectionInstance.getConnection();
			statement = dbConnection.prepareStatement(query);
			statement.executeQuery();
			logger.info("Student " + emailID + " has been kicked out");
		} catch (SQLException e) {
			logger.error("Error in creating add student to class.", e);
		} finally {
			dbConnectionInstance.closeConnection();
		}
	}

	@Override
	public void addStudentToClass(ArrayList<String> studentList, String className) {
		for (String student : studentList) {
			String query = "call add_student_to_class('" + student + "', '" + className + "');";
			try {
				dbConnection = dbConnectionInstance.getConnection();
				statement = dbConnection.prepareStatement(query);
				statement.executeQuery();
			} catch (SQLException e) {
				logger.error("Error in creating add student to class.", e);
			} finally {
				dbConnectionInstance.closeConnection();
			}
		}
	}

	@Override
	public void deleteClass(String className) {
		String query = "call delete_class('" + className + "');";
		try {
			dbConnection = dbConnectionInstance.getConnection();
			statement = dbConnection.prepareStatement(query);
			statement.executeQuery();
		} catch (SQLException e) {
			logger.error("Error in creating add student to class.", e);
		} finally {
			dbConnectionInstance.closeConnection();
		}
	}
}

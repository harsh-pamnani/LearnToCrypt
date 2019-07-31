package com.LearnToCrypt.Instructor;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IClassDAO;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ManageStudent {
    private static final Logger logger = LogManager.getLogger(ManageStudent.class);
    public static final String RESPONSE_ERROR = "Some error occurred in deleting the user. Please try again.";
    private DAOAbstractFactory daoAbstractFactory;

    public ManageStudent() {
        daoAbstractFactory = new DAOAbstractFactory();
    }

    public void deleteStudent(String sessionEmail, String userEnteredEmail) throws DeleteStudentException {
        IUserDAO userDAO = daoAbstractFactory.createUserDAO();

        if (userDAO.isUserRegistered(userEnteredEmail) && !sessionEmail.equalsIgnoreCase(userEnteredEmail) && userDAO.deleteUser(userEnteredEmail)) {
            logger.info(userEnteredEmail + " deleted successfully.");
        } else {
            logger.debug("Error occurred in deleting the user : " + userEnteredEmail);
            throw new DeleteStudentException(RESPONSE_ERROR);
        }
    }

    public void deleteStudentFromClass(String studentEmail, String Instructor){
        IClassDAO classDAO = daoAbstractFactory.createClassDAO();
        classDAO.deleteStudentFromClass(studentEmail);
        logger.info("Instructor \"" + Instructor + "\" deleted a student!");
    }

    public void addStudentToClass(MultipartFile file, String className, String Instructor){
        ArrayList<String>  studentList = readStudentList(file);
        IClassDAO classDAO = daoAbstractFactory.createClassDAO();
        classDAO.addStudentToClass(studentList,className);
        logger.info("Instructor \"" + Instructor + "\" added a list of to class "+className);
    }

    private ArrayList<String> readStudentList(MultipartFile file){
        ArrayList<String> result = new ArrayList<>();
        try {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while ((line = br.readLine()) != null) {
                result.add(line.split(",")[0]);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return result;
    }
}

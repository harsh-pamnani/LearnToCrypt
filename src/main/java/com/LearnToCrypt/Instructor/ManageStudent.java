package com.LearnToCrypt.Instructor;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManageStudent {
    private static final Logger logger = LogManager.getLogger(ManageStudent.class);
    public static final String RESPONSE_ERROR = "Some error occured in deleting the user. Please try again.";
    private DAOAbstractFactory daoAbstractFactory;

    public ManageStudent() {
        daoAbstractFactory = new DAOAbstractFactory();
    }

    protected void deleteStudent(String sessionEmail, String userEnteredEmail) throws DeleteStudentException {
        IUserDAO userDAO = daoAbstractFactory.createUserDAO();

        if (userDAO.isUserRegistered(userEnteredEmail) && !sessionEmail.equalsIgnoreCase(userEnteredEmail) && userDAO.deleteUser(userEnteredEmail)) {
            logger.info(userEnteredEmail + " deleted successfully.");
        } else {
            logger.debug("Error occurred in deleting the user : " + userEnteredEmail);
            throw new DeleteStudentException(RESPONSE_ERROR);
        }
    }
}

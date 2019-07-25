package com.LearnToCrypt.Instructor;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

public class ManageStudent {
    private static final Logger logger = LogManager.getLogger(StudentManagementController.class);
    public static final String RESPONSE_USER_DELETED = "User successfully deleted";
    public static final String RESPONSE_ERROR = "Some error occured in deleting the user. Please try again.";
    public static final String RESPONSE_SAME_EMAIL = "Please enter email address other than your email.";
    public static final String RESPONSE_NOT_REGISTERED = "Student is not registered with the system.";

    private AuthenticationManager authenticationManager;
    private DAOAbstractFactory daoAbstractFactory;

    public ManageStudent() {
        authenticationManager = AuthenticationManager.instance();
        daoAbstractFactory = new DAOAbstractFactory();
    }

    protected String deleteStudentAndGetResponse(String sessionEmail, String userEnteredEmail) {
        IUserDAO userDAO = daoAbstractFactory.createUserDAO();

        String formResponseMessage = "";
        if (userDAO.isUserRegistered(userEnteredEmail)) {
            if (!sessionEmail.equalsIgnoreCase(userEnteredEmail)) {
                if (userDAO.deleteUser(userEnteredEmail)) {
                    logger.info(userEnteredEmail + " deleted successfully.");
                    formResponseMessage = RESPONSE_USER_DELETED;
                } else {
                    logger.debug("Error occurred in deleting the user : " + userEnteredEmail);
                    formResponseMessage = RESPONSE_ERROR;
                }
            } else {
                logger.debug(userEnteredEmail + " is same as logged in user email.");
                formResponseMessage = RESPONSE_SAME_EMAIL;
            }
        } else {
            logger.debug(userEnteredEmail + " is not registered with the system.");
            formResponseMessage = RESPONSE_NOT_REGISTERED;
        }
        return formResponseMessage;
    }
}

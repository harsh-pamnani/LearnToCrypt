package com.LearnToCrypt.Instructor;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class StudentManagementController {

	private static final Logger logger = LogManager.getLogger(StudentManagementController.class);
    private AuthenticationManager authenticationManager;
    private DAOAbstractFactory daoAbstractFactory;
    
    public StudentManagementController() {
        authenticationManager = AuthenticationManager.instance();
        daoAbstractFactory = new DAOAbstractFactory();
    }
    
	@GetMapping("/studentManagement")
    public String displayStudentManagement(HttpSession httpSession, ModelMap model) {
		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        }
      
        String username = authenticationManager.getUsername(httpSession);
        model.put("username", username);
        
        return "studentManagement";
	}
	
	@PostMapping("/studentManagement")
	public String removeStudent(HttpSession httpSession, ModelMap model, @RequestParam String email) {
		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        }
        String username = authenticationManager.getUsername(httpSession);
        model.put("username", username);
        
		IUserDAO userDAO = daoAbstractFactory.createUserDAO();
		
		model.addAttribute("isErrorPresent", "Yes");
		String formResponseMessage = "";
		if (userDAO.isUserRegistered(email)) {
			if (!authenticationManager.getEmail(httpSession).equals(email)) {
				IUserDAO userDAOForDelete = daoAbstractFactory.createUserDAO();
				if (userDAOForDelete.deleteUser(email)) {
					model.addAttribute("isErrorPresent", "No");
					logger.info(email + " deleted successfully.");
					formResponseMessage = "User successfully deleted";
				} else {
					logger.debug("Error occurred in deleting the user : " + email);
					formResponseMessage = "Some error occured in deleting the user. Please try again.";
				}
			} else {
				logger.debug(email + " is same as logged in user email.");
				formResponseMessage = "Please enter email address other than your email.";
			}
		} else {
			logger.debug(email + " is not registered with the system.");
			formResponseMessage = "Student is not registered with the system.";
		}
		
		model.addAttribute("formResponseMessage", formResponseMessage);
        return "studentManagement";
	}
}

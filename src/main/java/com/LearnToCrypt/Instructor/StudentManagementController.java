package com.LearnToCrypt.Instructor;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class StudentManagementController {
    private static final Logger logger = LogManager.getLogger(ClassManagementController.class);
    public static final String USER_SUCCESSFULLY_DELETED = "User Successfully Deleted";
    private AuthenticationManager authenticationManager;
    private ManageStudent manageStudent;
    
    public StudentManagementController() {
        authenticationManager = AuthenticationManager.instance();
        manageStudent = new ManageStudent();
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

        String sessionEmail = authenticationManager.getEmail(httpSession);
        try {
            manageStudent.deleteStudent(sessionEmail, email);
            model.addAttribute("isErrorPresent", "No");
            model.addAttribute("formResponseMessage", USER_SUCCESSFULLY_DELETED);
            logger.info(email + " deleted successfully");
        } catch (DeleteStudentException e) {
            model.addAttribute("isErrorPresent", "Yes");
            model.addAttribute("formResponseMessage", e.getMessage());
            logger.error("Error occurred in deleting the user : " + email, e);
        }

        return "studentManagement";
	}
}

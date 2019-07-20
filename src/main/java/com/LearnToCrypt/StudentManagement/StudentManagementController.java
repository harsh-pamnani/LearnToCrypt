package com.LearnToCrypt.StudentManagement;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;

@Controller
public class StudentManagementController {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
    private AuthenticationManager authenticationManager;

    public StudentManagementController() {
        authenticationManager = AuthenticationManager.instance();
    }
    
	@GetMapping("/studentManagement")
    public String displayStudentManagement(HttpSession httpSession, ModelMap model) {
//		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
//        if(!isUserAuthenticated) {
//            return "redirect:/login";
//        }
        
//        String username = authenticationManager.getUsername(httpSession);
//        model.put("username", username);
        
        return "studentManagement";
	}
	
	@PostMapping("/studentManagement")
	public String removeStudent(HttpSession httpSession, ModelMap model, @RequestParam String email) {
//		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
//        if(!isUserAuthenticated) {
//            return "redirect:/login";
//        }
//        
        model.addAttribute("isErrorPresent", "No");
        model.addAttribute("formResponseMessage", "Some error occurred during student deletion. Please try again. ");
        System.out.println("Student to be removed is : " + email);
        return "studentManagement";
        
	}
}

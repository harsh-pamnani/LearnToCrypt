package com.LearnToCrypt.Instructor;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class StudentManagementController {

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
		String formResponseMessage = manageStudent.deleteStudentAndGetResponse(sessionEmail, email);

		model.addAttribute("formResponseMessage", formResponseMessage);
		model.addAttribute("isErrorPresent", "Yes");
		if(ManageStudent.RESPONSE_USER_DELETED.equals(formResponseMessage)) {
			model.addAttribute("isErrorPresent", "No");
		}
        return "studentManagement";
	}


}

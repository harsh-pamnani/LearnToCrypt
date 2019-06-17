package com.LearnToCrypt.SignUp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

@Controller
public class SignUpController implements WebMvcConfigurer {
	
	IDAOAbstractFactory abstractFactory;
	ValidateSignUpForm validateSignUpForm;
	
	public SignUpController() {
		 abstractFactory = new DAOAbstractFactory();
		 validateSignUpForm = new ValidateSignUpForm();
	}
	
	@GetMapping("/signup")
    public String displaySignUp(ModelMap model) {
		
		model.addAttribute("user", new User());

		return "registration.html";
    }
	
	@PostMapping("/signup")
    public String showDashboard(ModelMap model,User user,@RequestParam String confirmPassword,RedirectAttributes redirectAttributes) {
		
		String formError = validateSignUpForm.validateFormDetails(user, confirmPassword);
		
		if( formError.equals("") ) {		
			IUserDAO userDAOValidation = abstractFactory.createUserDAO();
			boolean isUserRegistered = userDAOValidation.isUserRegistered(user);
			
			if (isUserRegistered) {
				model.put("invalidSignup", "Email id is already registered. Registration Failed.");
				return "registration.html";
			} else {
				IUserDAO userDAORegistration = abstractFactory.createUserDAO();
				userDAORegistration.createUser(user);
			}
		} else {
			model.put("invalidSignup", formError + " Registration Failed.");
			return "registration.html";
		}
		
		IUserDAO userDAOName = abstractFactory.createUserDAO();
		String userName = userDAOName.getUserName(user.getEmail());
		
		// This logic will be updated to get the user's name from database.
		redirectAttributes.addFlashAttribute("username", userName);
		
        return "redirect:/dashboard";
    }
}
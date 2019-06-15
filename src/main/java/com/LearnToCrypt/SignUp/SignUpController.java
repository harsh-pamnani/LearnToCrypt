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
    public String displayLogin(ModelMap model) {
		
		model.addAttribute("user", new User());

		return "registration.html";
    }
	
	@PostMapping("/signup")
    public String showDashboard(ModelMap model,User user,@RequestParam String confirmPassword,RedirectAttributes redirectAttributes) {
		
		String formError = validateSignUpForm.validateFormDetails(user, confirmPassword);
		
		if( formError.equals("") ) {
			IUserDAO userDAO = abstractFactory.createUserDAO();
			userDAO.createUser(user);
		} else {
			model.put("invalidSignup", formError + " Registration Failed.");
			return "registration.html";
		}
		
        return "redirect:/dashboard";
    }
}
package com.LearnToCrypt.SignUp;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.LearnToCrypt.services.SignUpValidatorService;

@Controller
public class SignUpController implements WebMvcConfigurer {
	
	@Autowired
    SignUpValidatorService singUpService;
	
	IDAOAbstractFactory abstractFactory;
	
	public SignUpController() {
		 abstractFactory = new DAOAbstractFactory();
	}
	
	@GetMapping("/signup")
    public String displayLogin(ModelMap model) {
		
		
		model.addAttribute("user", new User());

		return "registration.html";
    }
	
	@PostMapping("/signup")
    public String showDashboard(ModelMap model,User user,@RequestParam String confirmPassword,RedirectAttributes redirectAttributes) {
		
		
		System.out.println("Name : "+ user.getName());
		System.out.println("HP : " + confirmPassword);
		
		IUserDAO userDAO = abstractFactory.createUserDAO();
		userDAO.createUser(user);
		
//		String isSignUpValidError = singUpService.validateSignUpForm(username, email, password, confirmPassword);
//        
//		if (!isSignUpValidError.equals("")) {
//			model.addAttribute("username", username);
//			model.put("email", email);
//            model.put("invalidSignup", isSignUpValidError + ". Registration Failed.");
//            return "registration";
//        }
//		// This logic will be updated to get the user's name from database.
//		redirectAttributes.addFlashAttribute("username", "Harsh New User");
//		model.put("email", email);
//        model.put("password", password);
//        
//        
        
        return "redirect:/dashboard";
    }
}
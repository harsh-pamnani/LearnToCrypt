package com.LearnToCrypt.SignIn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.IBusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

@Controller
public class LoginController implements WebMvcConfigurer {

	ValidateUserCredentials validateUserCredentials;
	IDAOAbstractFactory daoAbstractFactory;
	IBusinessModelAbstractFactory businessModelAbstractFactory;

	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
    public LoginController() {
    	validateUserCredentials = new ValidateUserCredentials();
    	daoAbstractFactory = new DAOAbstractFactory();
    	businessModelAbstractFactory = new BusinessModelAbstractFactory();
    }
    
	@GetMapping("/login")
    public String displayLogin(ModelMap model) {
		
		model.addAttribute("user", businessModelAbstractFactory.createUser());
		
		return "login.html";
    }
	
	@PostMapping("/login")
    public String showDashboard(ModelMap model, User user, RedirectAttributes redirectAttributes) {
		
		// Credential validations will go here.
		boolean isUserValid = validateUserCredentials.validateCredentials(user);
        
		if (!isUserValid) {
			logger.error("User \""+user.getName()+"\" entered invalid credentials");
            model.put("invalidLogin", "Invalid Credentials");
            return "login";
        }
		
		IUserDAO userDAOName = daoAbstractFactory.createUserDAO();
		String userName = userDAOName.getUserName(user.getEmail());
		logger.error("User \""+user.getName()+"\" login success");
		redirectAttributes.addFlashAttribute("username", userName);
		
        return "redirect:/dashboard";
    }
}
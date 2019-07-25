package com.LearnToCrypt.SignUp;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.IBusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class SignUpController implements WebMvcConfigurer {

	public static final String ERROR_ALREADY_REGISTERED = "Email id is already registered. Registration Failed.";
	public static final String ERROR_REGISTRATION_FAILED = " Registration Failed.";
	IDAOAbstractFactory daoAbstractFactory;
	ValidateSignUpForm validateSignUpForm;
	IBusinessModelAbstractFactory businessModelAbstractFactory;

	private static final Logger logger = LogManager.getLogger(SignUpController.class);
	AuthenticationManager authenticationManager;

	public SignUpController() {
		daoAbstractFactory = new DAOAbstractFactory();
		validateSignUpForm = new ValidateSignUpForm();
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
		authenticationManager = AuthenticationManager.instance();
	}

	@GetMapping("/signup")
	public String displaySignUp(ModelMap model, HttpSession httpSession) {
		if (authenticationManager.isUserAuthenticated(httpSession)) {
			logger.info("/signup redirecting to dashboard as the user " + authenticationManager.getEmail(httpSession)
					+ " is already logged in");
			return "redirect:/dashboard";
		}

		model.addAttribute("user", businessModelAbstractFactory.createUser());
		return "registration.html";
	}

	@PostMapping("/signup")
	public String showDashboard(ModelMap model, User user, @RequestParam String confirmPassword,
			RedirectAttributes redirectAttributes) {

		String formError = validateSignUpForm.validateFormDetails(user, confirmPassword);
		IUserDAO userDAO = daoAbstractFactory.createUserDAO();

		if (formError.equals("")) {
			boolean isUserRegistered = userDAO.isUserRegistered(user.getEmail());

			if (isUserRegistered) {
				model.put("invalidSignup", ERROR_ALREADY_REGISTERED);
				logger.error("Email id \"" + user.getEmail() + "\" is already registered. Registration Failed.");
				return "registration.html";
			} else {
				IUserDAO userDAORegistration = daoAbstractFactory.createUserDAO();
				userDAORegistration.createUser(user);
				logger.info(user.getEmail() + " registration success.");
			}
		} else {
			model.put("invalidSignup", formError + ERROR_REGISTRATION_FAILED);
			logger.error(formError + ERROR_REGISTRATION_FAILED);
			return "registration.html";
		}

		String userName = userDAO.getUserName(user.getEmail());
		redirectAttributes.addFlashAttribute("username", userName);
		return "redirect:/dashboard";
	}
}
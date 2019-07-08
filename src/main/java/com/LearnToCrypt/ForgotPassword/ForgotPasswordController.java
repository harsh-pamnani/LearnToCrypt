package com.LearnToCrypt.ForgotPassword;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IPasswordUpdaterDAO;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.EmailService.EmailService;
import com.LearnToCrypt.EmailService.IEmailService;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class ForgotPasswordController implements WebMvcConfigurer {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
	private IDAOAbstractFactory abstractFactory;
	private IUserDAO userDAO;
	private IPasswordUpdaterDAO passwordUpdaterDAO;
	private IEmailService emailService;
	private AuthenticationManager authenticationManager;
	private User user;

	public ForgotPasswordController() {
		abstractFactory = new DAOAbstractFactory();
		userDAO = abstractFactory.createUserDAO();
		passwordUpdaterDAO = abstractFactory.createPasswordSetterDAO();
		emailService = new EmailService();
		user = new User();
		authenticationManager = AuthenticationManager.instance();
	}

	@GetMapping("/forgotpassword")
	public String displayForgotPass(ModelMap model,
									HttpSession httpSession) {
		logger.info("Loading forgot password page");
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			logger.info("User is logged in. Redirecting to dashboard");
			return("redirect:/dashboard");
		}
		return ("forgotpassword");
	}

	@PostMapping("/forgotpassword")
	public String resetPassword(ModelMap model,
								HttpServletRequest httpServletRequest,
								@RequestParam String email) {
		logger.info("Processing forgot password request");
		String errorText = null;
		String server = httpServletRequest.getServerName();
		String serverUrl;
		if (server.equals("localhost")) {
			serverUrl = httpServletRequest.getScheme() + "://" + server + ":" + httpServletRequest.getServerPort();
		}
		else {
			serverUrl = httpServletRequest.getScheme() + "://" + server;
		}
		String token;
		user.setEmail(email);
		if (userDAO.isUserRegistered(user)) {
			token = UUID.randomUUID().toString();
			String url = serverUrl + "/passwordchange?token=" + token;
			logger.info("Reset url for email: " + email + "is " + url);
			passwordUpdaterDAO.setResetToken(email, token);
			emailService.sendPassResetMail(email, url);
			model.put("successText", "Password reset email sent");
		}
		else {
			errorText = "User does not exist";
			logger.error("Error processing request: " + errorText);
			model.put("errorText", errorText);
		}
		return ("forgotpassword");
	}
}

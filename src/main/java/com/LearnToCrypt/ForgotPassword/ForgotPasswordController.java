package com.LearnToCrypt.ForgotPassword;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IPasswordUpdaterDAO;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.EmailService.EmailService;
import com.LearnToCrypt.EmailService.IEmailService;
import com.LearnToCrypt.SignIn.AuthenticationManager;
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
	public String displayForgotPass(ModelMap model) {
		return ("forgotpassword");
	}

	@PostMapping("/forgotpassword")
	public String resetPassword(ModelMap model,
								HttpSession httpSession,
								HttpServletRequest httpServletRequest,
								@RequestParam String email) {
		if(authenticationManager.isUserAuthenticated(httpSession)) {
			return("redirect:/dashboard");
		}
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
			passwordUpdaterDAO.setResetToken(email, token);
			emailService.sendPassResetMail(email, url);
			model.put("successText", "Password reset email sent");
		}
		else {
			errorText = "User does not exist";
			model.put("errorText", errorText);
		}
		return ("forgotpassword");
	}
}

package com.LearnToCrypt.ForgotPassword;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IPasswordUpdaterDAO;
import com.LearnToCrypt.DAO.IUserDAO;
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

	IDAOAbstractFactory abstractFactory;
	IUserDAO userDAO;
	IPasswordUpdaterDAO passwordUpdaterDAO;
	User user;

	public ForgotPasswordController() {
		abstractFactory = new DAOAbstractFactory();
		userDAO = abstractFactory.createUserDAO();
		passwordUpdaterDAO = abstractFactory.createPasswordSetterDAO();
		user = new User();
	}

	@PostMapping("/forgotpassword")
	public String resetPassword(ModelMap model,
								HttpServletRequest httpServletRequest,
								@RequestParam String email) {
		String errorText = null;
		String serverUrl = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName();
		String token;
		user.setEmail(email);
		if (userDAO.isUserRegistered(user)) {
			token = UUID.randomUUID().toString();
			String url = serverUrl + "/reset?token=" + token;
			passwordUpdaterDAO.setResetToken(email, url);
		}
		else {
			errorText = "User does not exist";
			model.put("errorText", errorText);
		}
		return null;
	}
}

package com.LearnToCrypt.Profile;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.DAO.IPasswordUpdaterDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class PasswordChangeController implements WebMvcConfigurer {

	private String resetToken;
	private IProfileValidator profileValidator;
	private IUserProfileBridge profile;
	private IPasswordUpdaterDAO passwordUpdaterDAO;
	private IDAOAbstractFactory abstractFactory;
	private IPasswordChanger passwordChanger;

	public PasswordChangeController() {
		abstractFactory = new DAOAbstractFactory();
		passwordUpdaterDAO = abstractFactory.createPasswordSetterDAO();
		passwordChanger = new ProfileUpdater();
	}

	@GetMapping("/passwordchange")
	public String displayPassChange(ModelMap model,
									@RequestParam String token) {
		resetToken = token;
		return ("passwordchange");
	}

	@GetMapping("/changesuccess")
	public String displaySuccessChange(ModelMap model) {
		return ("changesuccess");
	}

	@PostMapping("/passwordchange")
	public String resetPassword(ModelMap model,
								@RequestParam String newPass,
								@RequestParam String confirmPass) {
		if(null == newPass || null == confirmPass) {
			model.put("errorText", "Password cannot be empty");
			return ("passwordchange");
		}
		else {
			String email = passwordUpdaterDAO.getEmailFromToken(resetToken);
			profile = new UserProfile(email);
			profileValidator = new ProfileValidator(profile);
			String error = profileValidator.isPasswordValid(newPass, confirmPass);
			if (null == error) {
				passwordChanger.changePassword(email, newPass);
				return ("redirect:/changesuccess");
			}
			else {
				model.put("errorText", error);
				return ("passwordchange");
			}
		}
	}
}

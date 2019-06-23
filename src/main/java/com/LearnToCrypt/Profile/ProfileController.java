package com.LearnToCrypt.Profile;

import com.LearnToCrypt.SignIn.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController implements WebMvcConfigurer {

	private IUserProfileBridge profile;
	private IPasswordChanger passwordChanger;
	private IUserNameChanger userNameChanger;
	private String username;
	private String email;
	private AuthenticationManager authenticationManager;
	private IProfileValidator profileValidator;

	ProfileController() {
		passwordChanger = new ProfileUpdater();
		userNameChanger = new ProfileUpdater();
		authenticationManager = AuthenticationManager.instance();
	}

	@GetMapping("/profile")
	public String displayProfile(ModelMap model,
								 HttpSession httpSession,
								 @RequestParam (required = false) String errorText,
								 @RequestParam (required = false) String successText ) {
		if (authenticationManager.isUserAuthenticated(httpSession)) {
			email = authenticationManager.getEmail(httpSession);
			profile = new UserProfile(email);
			model.put("username", authenticationManager.getUsername(httpSession));
			model.put("email", profile.getEmail());
			model.put("role", profile.getRole());
			if (null != errorText) {
				model.put("errorText", errorText);
			}
			else if (null != successText) {
				model.put("successText", successText);
			}
			return ("profile");
		}
		else {
			return ("redirect:/login");
		}
	}

	@PostMapping("/profile")
	public String updateProfile(ModelMap model,
								HttpSession httpSession,
								@RequestParam String username,
								@RequestParam String newPass,
								@RequestParam String confirmPass) {

		email = authenticationManager.getEmail(httpSession);
		profile = new UserProfile(email);
		profileValidator = new ProfileValidator(profile);
		if(null != username && !username.equals(profile.getUserName())) {
			String error = profileValidator.isNameValid(username);
			if (null == error) {
				userNameChanger.changeName(email, username);
			}
			else {
				return ("redirect:/profile?errorText=" + error);
			}
		}

		if(null != newPass && !newPass.equals("")) {
			String error = profileValidator.isPasswordValid(newPass, confirmPass);
			if (null == error) {
				email = authenticationManager.getEmail(httpSession);
				passwordChanger.changePassword(email, newPass);
			}
			else {
				return ("redirect:/profile?errorText=" + error);
			}
		}
		return ("redirect:/profile?successText=Profile Updated Successfully");
	}
}

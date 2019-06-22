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
    private String username;
    private String email;
    private AuthenticationManager authenticationManager;

    ProfileController() {
        passwordChanger = new PasswordChanger();
        authenticationManager = AuthenticationManager.instance();
    }

    @GetMapping("/profile")
    public String displayProfile(ModelMap model,
                                 HttpSession httpSession) {
        email = authenticationManager.getEmail(httpSession);
        profile = new UserProfile(email);
        model.put("username", profile.getUserName());
        model.put("email", profile.getEmail());
        model.put("role", profile.getRole());
        return ("profile");
    }

    @PostMapping("/profile")
    public String changePassword(ModelMap model,
                                 HttpSession httpSession,
                                 @RequestParam String newPass,
                                 @RequestParam String confirmPass) {
        //TODO: Match Passwords and validate
        email = authenticationManager.getEmail(httpSession);
        passwordChanger.changePassword(email, newPass);
        return ("profile");
    }
}

package com.LearnToCrypt.Profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class ProfileController implements WebMvcConfigurer {

    private IProfile userProfile;
    private IPasswordChanger passwordChanger;
    private String username;
    private String email;

    ProfileController() {
        userProfile = new UserProfile();
        passwordChanger = new PasswordChanger();
        username = userProfile.getUserName();
        email = userProfile.getEmail();
    }

    @GetMapping("/profile")
    public String displayProfile(ModelMap model) {

        model.put("username", username);
        model.put("email", email);
        return ("profile");
    }

    @PostMapping("/profile")
    public String changePassword(ModelMap model,
                                 @RequestParam String newPass,
                                 @RequestParam String confirmPass) {

        passwordChanger.changePassword(email, newPass);
        return ("profile");
    }
}

package com.LearnToCrypt.MyProgress;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class MyProgressController implements WebMvcConfigurer {

    private static final Logger logger = LogManager.getLogger(MyProgressController.class);
    private AuthenticationManager authenticationManager;
    private ProgressParameter progressParameter;

    public MyProgressController() {
        this.authenticationManager = AuthenticationManager.instance();
    }

    @GetMapping("/myProgress")
    public String showProgress(HttpSession httpSession, ModelMap model){

        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        }
        String email = authenticationManager.getEmail(httpSession);
        String username = authenticationManager.getUsername(httpSession);
        model.put("username", username);
        progressParameter = new ProgressParameter(email);
        String[] progress = progressParameter.getProgressList();

        if(progress != null){
            for(int i = 0;i<progress.length;i++){
                model.addAttribute(progress[i].replaceAll("\\s", ""),"block");
            }
        }
        model.addAttribute("count", progressParameter.getProgress());
        logger.info("user \""+username+"\" accessed MyProgress page");
        return "myProgress";
    }

}

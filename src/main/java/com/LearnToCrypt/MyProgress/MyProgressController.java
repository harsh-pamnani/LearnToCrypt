package com.LearnToCrypt.MyProgress;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;

@Controller
public class MyProgressController implements WebMvcConfigurer {

    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
    private AuthenticationManager authenticationManager;
    private DAOAbstractFactory daoAbstractFactory;

    public MyProgressController() {
        this.authenticationManager = AuthenticationManager.instance();
        this.daoAbstractFactory = new DAOAbstractFactory();
    }

    @GetMapping("/myProgress")
    public String showProgress(HttpSession httpSession, ModelMap model){

        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        }

        String email = authenticationManager.getEmail(httpSession);
        String username = authenticationManager.getUsername(httpSession);
        String[] progress;
        String[] algList;
        int total = 0;
        int completed = 0;
        model.put("username", username);

        String userClass = daoAbstractFactory.createUserDAO().getUserClass(email);
        if (userClass != null){
            algList = daoAbstractFactory.createAlgorithmDAO().getAlgList(userClass);
            progress = daoAbstractFactory.createUserDAO().getProgress(email);
            if(progress != null){
                completed = progress.length;
                for(int i = 0;i<progress.length;i++){
                    model.addAttribute(progress[i].replaceAll("\\s", ""),"block");
                }
            }
            total = algList.length;
        }
        model.addAttribute("count",completed+" / "+total);
        logger.info("user \""+username+"\" accessed MyProgress page");
        return "myProgress";
    }

}

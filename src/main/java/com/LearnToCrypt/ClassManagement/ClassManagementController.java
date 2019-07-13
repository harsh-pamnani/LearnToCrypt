package com.LearnToCrypt.ClassManagement;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ClassManagementController {

    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

    AuthenticationManager authenticationManager;
    DAOAbstractFactory daoAbstractFactory;

    public ClassManagementController() {
        authenticationManager = AuthenticationManager.instance();
        daoAbstractFactory = new DAOAbstractFactory();
    }

    @GetMapping("/classManagement")
    public String displayClassManagement(HttpSession httpSession, ModelMap model) {
        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String email = authenticationManager.getEmail(httpSession);
            String role = daoAbstractFactory.createUserDAO().getUserRole(email);

            String username = authenticationManager.getUsername(httpSession);
            model.put("username", username);

            logger.info("user \"" + username + "\" accessed dashboard!");

        }
        return "classManagement";
    }
}


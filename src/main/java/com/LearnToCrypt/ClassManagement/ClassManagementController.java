package com.LearnToCrypt.ClassManagement;

import com.LearnToCrypt.BusinessModels.MyClass;
import com.LearnToCrypt.DAO.ClassDAO;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IClassDAO;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

            DAOAbstractFactory abstractFactory = new DAOAbstractFactory();
            IClassDAO classDAO = abstractFactory.createClassDAO();
            //classDAO.createClass(new MyClass("new class","tonyt@dal.ca","1,2,3"));
            model.addAttribute("classes",classDAO.getClass(email));

            logger.info("Instructor \"" + username + "\" accessed class management!");

        }
        return "classManagement";
    }
    @GetMapping("/classManagement/delete")
    public String removeStudentFromClass(HttpSession httpSession, ModelMap model,
                                         @RequestParam(name = "id", required=false, defaultValue="example@example.org")String emailID){
        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            DAOAbstractFactory abstractFactory = new DAOAbstractFactory();
            IClassDAO classDAO = abstractFactory.createClassDAO();
            classDAO.deleteStudentFromClass(emailID);
        }
        return "redirect:/classManagement";
    }
}


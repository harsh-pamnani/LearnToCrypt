package com.LearnToCrypt.Instructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.IBusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.MyClass;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.DAO.IClassDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;

@Controller
public class ClassManagementController {

    private static final Logger logger = LogManager.getLogger(ClassManagementController.class);

    private AuthenticationManager authenticationManager;
    private IBusinessModelAbstractFactory businessModelAbstractFactory;
    private ManageStudent manageStudent;
    private ManageClass manageClass;


    public ClassManagementController() {
        authenticationManager = AuthenticationManager.instance();
        businessModelAbstractFactory = new BusinessModelAbstractFactory();
        manageStudent = new ManageStudent();
        manageClass = new ManageClass();
    }

    @GetMapping("/classManagement")
    public String displayClassManagement(HttpSession httpSession, ModelMap model) {
        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String email = authenticationManager.getEmail(httpSession);
            String role = authenticationManager.getUserRole(httpSession);
            String username = authenticationManager.getUsername(httpSession);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }

            model.put("username", username);

            model.addAttribute("classes",manageClass.getClasses(email));
            model.addAttribute("algorithms",manageClass.getAllAvaiableAlgorithmForClass());

            model.addAttribute("myNewClass", businessModelAbstractFactory.createMyClass());
            logger.info("Instructor \"" + username + "\" accessed class management!");

        }
        return "classManagement";
    }

    @GetMapping("/classManagement/deleteStudent")
    public String removeStudentFromClass(HttpSession httpSession, ModelMap model,
                                         @RequestParam(name = "id", required=false, defaultValue="example@example.org")String emailID){
        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String role = authenticationManager.getUserRole(httpSession);
            String username = authenticationManager.getUsername(httpSession);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }
            manageStudent.deleteStudentFromClass(emailID,username);
        }
        return "redirect:/classManagement";
    }

    @GetMapping("/classManagement/deleteClass")
    public String deleteClass(HttpSession httpSession, ModelMap model,
                              @RequestParam("classToDelete_actual") String className){
        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String role = authenticationManager.getUserRole(httpSession);
            String username = authenticationManager.getUsername(httpSession);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }
            manageClass.deleteClass(className,username);
        }


        return "redirect:/classManagement";
    }

    @PostMapping("/classManagement/addClass")
    public String addClass(HttpSession httpSession, ModelMap model,
                           MyClass myNewClass,
                           @RequestParam(value = "classAlg" , required = false) String[] classAlg){

        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String email = authenticationManager.getEmail(httpSession);
            String role = authenticationManager.getUserRole(httpSession);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }

            manageClass.addClass(myNewClass.getClassName(),email,classAlg);
        }

        return "redirect:/classManagement";
    }

    @PostMapping("/classManagement/addStudent")
    public String addStudents(HttpSession httpSession, ModelMap model,
                              @RequestParam("studentList") MultipartFile file,
                              @RequestParam("classID") String className){
        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String role = authenticationManager.getUserRole(httpSession);
            String username = authenticationManager.getUsername(httpSession);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }
            if (!file.isEmpty()){
                manageStudent.addStudentToClass(file,className,username);
            }
        }

        return "redirect:/classManagement";
    }
}


package com.LearnToCrypt.Instructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

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
import com.LearnToCrypt.app.LearnToCryptApplication;

@Controller
public class ClassManagementController {

    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

    private AuthenticationManager authenticationManager;
    private DAOAbstractFactory daoAbstractFactory;
    private IBusinessModelAbstractFactory businessModelAbstractFactory;
    private DAOAbstractFactory abstractFactory;
    private IClassDAO classDAO;

    public ClassManagementController() {
        authenticationManager = AuthenticationManager.instance();
        daoAbstractFactory = new DAOAbstractFactory();
        businessModelAbstractFactory = new BusinessModelAbstractFactory();
        abstractFactory = new DAOAbstractFactory();
        classDAO = abstractFactory.createClassDAO();
    }

    @GetMapping("/classManagement")
    public String displayClassManagement(HttpSession httpSession, ModelMap model) {
        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String email = authenticationManager.getEmail(httpSession);
            String role = daoAbstractFactory.createUserDAO().getUserRole(email);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }
            String username = authenticationManager.getUsername(httpSession);
            model.put("username", username);

            IAlgorithmDAO algorithmDAO = abstractFactory.createAlgorithmDAO();
            model.addAttribute("classes",classDAO.getClass(email));
            model.addAttribute("algorithms",algorithmDAO.getAllAvailableAlgorithm());

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
            String email = authenticationManager.getEmail(httpSession);
            String role = daoAbstractFactory.createUserDAO().getUserRole(email);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }
            classDAO.deleteStudentFromClass(emailID);
        }
        String username = authenticationManager.getUsername(httpSession);
        logger.info("Instructor \"" + username + "\" deleted a student!");
        return "redirect:/classManagement";
    }

    @GetMapping("/classManagement/deleteClass")
    public String deleteClass(HttpSession httpSession, ModelMap model,
                              @RequestParam("classToDelete_actual") String className){
        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String email = authenticationManager.getEmail(httpSession);
            String role = daoAbstractFactory.createUserDAO().getUserRole(email);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }
            classDAO.deleteClass(className);
        }
        String username = authenticationManager.getUsername(httpSession);
        logger.info("Instructor \"" + username + "\" deleted a class!");
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
            String role = daoAbstractFactory.createUserDAO().getUserRole(email);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }
            String alg = "";
            if(classAlg != null) {
                for (String s: classAlg
                ) {
                    alg += s+",";
                }
            }
            classDAO.createClass(new MyClass(myNewClass.getClassName(),email,alg));
        }
        String username = authenticationManager.getUsername(httpSession);
        logger.info("Instructor \"" + username + "\" added a class!");
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
            String email = authenticationManager.getEmail(httpSession);
            String role = daoAbstractFactory.createUserDAO().getUserRole(email);
            if(!role.equals("Instructor")) {
                return "dashboard";
            }
            if (!file.isEmpty()){
                ArrayList<String> studentList = readStudentList(file);
                classDAO.addStudentToClass(studentList,className);
            }
        }
        String username = authenticationManager.getUsername(httpSession);
        logger.info("Instructor \"" + username + "\" added a list of to class "+className);
        return "redirect:/classManagement";
    }

    private ArrayList<String> readStudentList(MultipartFile file){
        ArrayList<String> result = new ArrayList<>();
        try {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while ((line = br.readLine()) != null) {
                result.add(line.split(",")[0]);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return result;
    }
}


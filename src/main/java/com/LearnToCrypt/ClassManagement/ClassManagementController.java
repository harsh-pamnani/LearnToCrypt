package com.LearnToCrypt.ClassManagement;

import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.IBusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.MyClass;
import com.LearnToCrypt.DAO.*;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassManagementController {

    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

    AuthenticationManager authenticationManager;
    DAOAbstractFactory daoAbstractFactory;
    IBusinessModelAbstractFactory businessModelAbstractFactory;

    public ClassManagementController() {
        authenticationManager = AuthenticationManager.instance();
        daoAbstractFactory = new DAOAbstractFactory();
        businessModelAbstractFactory = new BusinessModelAbstractFactory();
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
            IAlgorithmDAO algorithmDAO = abstractFactory.createAlgorithmDAO();
            //classDAO.createClass(new MyClass("new class","tonyt@dal.ca","1,2,3"));
            model.addAttribute("classes",classDAO.getClass(email));
            model.addAttribute("algorithms",algorithmDAO.getAllAvailableAlgorithm());

            model.addAttribute("myNewClass", businessModelAbstractFactory.createMyClass());
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

    @PostMapping("/classManagement/addClass")
    public String addClass(HttpSession httpSession, ModelMap model,
                           MyClass myNewClass,
                           @RequestParam(value = "classAlg" , required = false) String[] classAlg){

        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        } else {
            String email = authenticationManager.getEmail(httpSession);
            String alg = "";
            if(classAlg != null) {
                for (String s: classAlg
                ) {
                    alg += s+",";
                }
            }
            DAOAbstractFactory abstractFactory = new DAOAbstractFactory();
            IClassDAO classDAO = abstractFactory.createClassDAO();
            classDAO.createClass(new MyClass(myNewClass.getClassName(),email,alg));
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
            if (!file.isEmpty()){
                ArrayList<String> studentList = readStudentList(file);
                DAOAbstractFactory abstractFactory = new DAOAbstractFactory();
                IClassDAO classDAO = abstractFactory.createClassDAO();
                classDAO.addStudentToClass(studentList,className);
            }
        }
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
            System.err.println(e.getMessage());
        }
        return result;
    }
}


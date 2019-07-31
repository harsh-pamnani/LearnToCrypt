package com.LearnToCrypt.Instructor;


import com.LearnToCrypt.BusinessModels.MyClass;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.DAO.IClassDAO;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ManageClass {
    private static final Logger logger = LogManager.getLogger(ManageStudent.class);
    private IDAOAbstractFactory daoAbstractFactory;

    public ManageClass() {
        daoAbstractFactory = new DAOAbstractFactory();
    }

    public List<MyClass> getClasses(String emailID){
        IClassDAO classDAO = daoAbstractFactory.createClassDAO();
        return classDAO.getClass(emailID);
    }

    public ArrayList<String> getAllAvaiableAlgorithmForClass(){
        IAlgorithmDAO algorithmDAO = daoAbstractFactory.createAlgorithmDAO();
        return algorithmDAO.getAllAvailableAlgorithm();
    }

    public void deleteClass(String className,String Instructor){
        IClassDAO classDAO = daoAbstractFactory.createClassDAO();
        classDAO.deleteClass(className);
        logger.info("class: "+className+" is deleted by "+Instructor);
    }

    public void addClass(String className,String email,String[] classAlg){
        String alg = "";
        if(classAlg != null) {
            for (String s: classAlg
            ) {
                alg += s+",";
            }
        }
        MyClass myClass = new MyClass(className,email,alg);
        IClassDAO classDAO = daoAbstractFactory.createClassDAO();
        classDAO.createClass(myClass);
        logger.info("Instructor \"" + email + "\" added a new class: "+className);
    }
}

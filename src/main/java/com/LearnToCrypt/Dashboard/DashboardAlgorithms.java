package com.LearnToCrypt.Dashboard;

import com.LearnToCrypt.BusinessModels.Algorithm;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.DAO.IUserDAO;
import org.springframework.ui.ModelMap;

import java.util.List;

public class DashboardAlgorithms {
    DAOAbstractFactory daoAbstractFactory;

    public DashboardAlgorithms() {
        daoAbstractFactory = new DAOAbstractFactory();
    }

    public void addAlgorithmsToDashboard(String email, ModelMap model) {
        IAlgorithmDAO algorithmDAO = daoAbstractFactory.createAlgorithmDAO();
        IUserDAO userDAO = daoAbstractFactory.createUserDAO();
        String className = userDAO.getUserClass(email);

        List<Algorithm> basicAlgorithm = algorithmDAO.getAlgorithmByLevelAndClass(1,className);
        List<Algorithm> intermediateAlgorithm = algorithmDAO.getAlgorithmByLevelAndClass(2,className);

        if(!(basicAlgorithm == null) && basicAlgorithm.size()>=1){
            model.addAttribute("subtitle1","Basic encryption algorithm");
            model.addAttribute("basic",basicAlgorithm);
        }

        if(!(intermediateAlgorithm == null) && intermediateAlgorithm.size()>=1){
            model.addAttribute("subtitle2","Intermediate encryption algorithm");
            model.addAttribute("intermediate",intermediateAlgorithm);
        }

        if(userDAO.getUserClass(email) == null){
            model.addAttribute("subtitle1","You have not been assigned to any class yet.");
        }
    }
}

package com.LearnToCrypt.Algorithm;

import com.LearnToCrypt.BusinessModels.Algorithm;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.Instructor.ClassManagementController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManageAlgorithm {

    private static final Logger logger = LogManager.getLogger(ClassManagementController.class);

    IDAOAbstractFactory daoAbstractFactory;

    public ManageAlgorithm() {
        this.daoAbstractFactory = new DAOAbstractFactory();
    }

    public Algorithm getAlgorithm(String algName){
        IAlgorithmDAO algorithmDAO = daoAbstractFactory.createAlgorithmDAO();
        logger.info("get algorithm: "+algName);
        return algorithmDAO.getAlgorithm(algName);
    }
}

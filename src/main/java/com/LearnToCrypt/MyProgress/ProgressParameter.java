package com.LearnToCrypt.MyProgress;

import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IDAOAbstractFactory;
import com.LearnToCrypt.Instructor.ManageStudent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProgressParameter {
    private String[] progressList;
    private String[] algList;
    private int total = 0;
    private int completed = 0;
    private static final Logger logger = LogManager.getLogger(ManageStudent.class);
    private IDAOAbstractFactory daoAbstractFactory;

    public ProgressParameter(String email) {
        this.daoAbstractFactory = new DAOAbstractFactory();
        String userClass = daoAbstractFactory.createUserDAO().getUserClass(email);
        if (userClass != null){
            algList = daoAbstractFactory.createAlgorithmDAO().getAlgList(userClass);
            progressList = daoAbstractFactory.createUserDAO().getProgress(email);
            total = algList.length;
            completed = progressList.length;
        }
    }


    public String[] getAlgList() {
        return algList;
    }

    public int getTotal() {
        return total;
    }

    public int getCompleted() {
        return completed;
    }

    public String[] getProgressList() {
        return progressList;
    }

    public String getProgress(){
        return completed+" / "+total;
    }
}

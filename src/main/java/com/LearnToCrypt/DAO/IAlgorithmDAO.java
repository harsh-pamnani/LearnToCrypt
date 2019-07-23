package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.Algorithm;

import java.util.ArrayList;

public interface IAlgorithmDAO {
    public Algorithm getAlgorithm(String algorithmName);
    public ArrayList<String> getAllAvailableAlgorithm();
    public ArrayList<Algorithm> getAlgorithmByLevelAndClass(int lv,String className);
    public String[] getAlgList(String className);
}

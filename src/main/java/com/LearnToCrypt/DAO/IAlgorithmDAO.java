package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.Algorithm;

public interface IAlgorithmDAO {
    public Algorithm getAlgorithm(String algorithmName);
}

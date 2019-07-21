package com.LearnToCrypt.DAO;

import java.util.ArrayList;

import com.LearnToCrypt.BusinessModels.Algorithm;

public class AlgorithmDAOFactoryMock implements IAlgorithmDAO {

	@Override
	public Algorithm getAlgorithm(String algorithmName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllAvailableAlgorithm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Algorithm> getAlgorithmByLevelAndClass(int lv, String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getAlgList(String className) {
		// TODO Auto-generated method stub
		return null;
	}
}

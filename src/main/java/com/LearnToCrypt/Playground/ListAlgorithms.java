package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IAlgorithmFactory;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithm;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class ListAlgorithms implements IListAlgorithms {

	private static final Logger logger = LogManager.getLogger(ListAlgorithms.class);
	private ArrayList<String> algorithmNames;
	private HashMap<String, IEncryptionAlgorithm> algorithms;

	public ListAlgorithms(IAlgorithmDAO algorithmDAO, IAlgorithmFactory algorithmFactory) {
		logger.info("Populating available algorithms list");
		algorithmNames = algorithmDAO.getAllAvailableAlgorithm();
		algorithms = new HashMap<>();
		try {
			for (String name : algorithmNames) {
				logger.info("Adding " + name + " to the list of algorithms");
				algorithms.put(name, algorithmFactory.createAlgorithm(name));
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("Invalid name for the algorithm: " + e.getMessage());
		}
	}

	@Override
	public ArrayList<String> getAvailableAlgorithms() {
		return algorithmNames;
	}

	@Override
	public IEncryptionAlgorithm getAlgorithmWithName(String name) {
		return algorithms.get(name);
	}
}

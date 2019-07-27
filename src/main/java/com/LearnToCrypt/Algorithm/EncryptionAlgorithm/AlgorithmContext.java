package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;

import com.LearnToCrypt.Algorithm.UserInput;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

public class AlgorithmContext {

	private IUserDAO userDAO;
	private DAOAbstractFactory abstractFactory;

	private IEncryptionAlgorithmStrategy encryptionAlgorithmStrategy;
	private static final Logger logger = LogManager.getLogger(AlgorithmContext.class);

	public AlgorithmContext(IEncryptionAlgorithmStrategy encryptionAlgorithmStrategy) {
		this.encryptionAlgorithmStrategy = encryptionAlgorithmStrategy;
		abstractFactory = new DAOAbstractFactory();
		userDAO = abstractFactory.createUserDAO();
	}

	public void executeStrategy(UserInput userInput, String useremail, Model model) {
		// All encryption algorithms will implement keyPlainTextValidation(),
		// encode(), getResult(), and getSteps() method in its own way.
		// Encryption algorithm will be decided based on "encryptionAlgorithmStrategy".
		try {
			encryptionAlgorithmStrategy.keyPlainTextValidation(userInput);

			encryptionAlgorithmStrategy.encode(userInput.getKey() + "", userInput.getPlaintext());

			String result = encryptionAlgorithmStrategy.getResult();
			model.addAttribute("result", result);

			String steps = encryptionAlgorithmStrategy.getSteps();
			model.addAttribute("steps", steps);

			userDAO.updateProgress(useremail, this.encryptionAlgorithmStrategy.getName());
		} catch (KeyPlaintextFailureException e) {
			model.addAttribute("invalidInput", e.getMessage());
			logger.error("Key Validation Error: " + e.getMessage());
		}
	}
}

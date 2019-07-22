package com.LearnToCrypt.Algorithm.EncryptionAlgorithm;

import org.springframework.ui.Model;

import com.LearnToCrypt.Algorithm.UserInput;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

public class AlgorithmContext {

	private IUserDAO userDAO;
	private DAOAbstractFactory abstractFactory;

	private IEncryptionAlgorithmStrategy encryptionAlgorithmStrategy;

	public AlgorithmContext(IEncryptionAlgorithmStrategy encryptionAlgorithmStrategy) {
		this.encryptionAlgorithmStrategy = encryptionAlgorithmStrategy;
		abstractFactory = new DAOAbstractFactory();
		userDAO = abstractFactory.createUserDAO();
	}

	public void executeStrategy(UserInput userInput, String useremail, Model model) {
		String formError = encryptionAlgorithmStrategy.keyPlainTextValidation(userInput);

		if (formError == null) {
			encryptionAlgorithmStrategy.encode(userInput.getKey() + "", userInput.getPlaintext());

			String result = encryptionAlgorithmStrategy.getResult();
			model.addAttribute("result", result);

			String steps = encryptionAlgorithmStrategy.getSteps();
			model.addAttribute("steps", steps);

			userDAO.updateProgress(useremail, this.encryptionAlgorithmStrategy.getName());
		} else {
			model.addAttribute("invalidInput", formError);
		}
	}
}

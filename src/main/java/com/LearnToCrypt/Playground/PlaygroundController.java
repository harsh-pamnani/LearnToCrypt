package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.AlgorithmFactory;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IAlgorithmFactory;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;
import com.LearnToCrypt.DAO.AlgorithmDAO;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.FileToString.FileToString;
import com.LearnToCrypt.FileToString.IFileToString;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PlaygroundController implements WebMvcConfigurer {
	private static final Logger logger = LogManager.getLogger(PlaygroundController.class);

	private AuthenticationManager authenticationManager;
	private ArrayList<String> algorithms;
	private IListAlgorithms algorithmList;
	private IAlgorithmDAO algorithmDAO;
	private IAlgorithmFactory algorithmFactory;
	private IFileToString fileToString;
	private IComparisonParameters comparisonParameters;
	private ICompare compare;
	private IComparisonResultSet comparisonResultSet;

	public PlaygroundController() {
		authenticationManager = AuthenticationManager.instance();
		algorithmDAO = new AlgorithmDAO();
		algorithmFactory = new AlgorithmFactory();
		algorithmList = new ListAlgorithms(algorithmDAO, algorithmFactory);
		algorithms = algorithmList.getAvailableAlgorithms();
		fileToString = new FileToString();
		comparisonParameters = new ComparisonParameters();
		compare = new Compare();
	}

	@GetMapping("/playground")
	public String displayPlayground(HttpSession httpSession, ModelMap model,
									@RequestParam(required = false) String errorText) {
		logger.info("Loading Playground Page");
		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
		if(!isUserAuthenticated) {
			logger.error("User is not logged in. Redirecting to Login page");
			return "redirect:/login";
		} else {
			String username = authenticationManager.getUsername(httpSession);
			model.put("username", username);
			model.put("leftAlgorithms", algorithms);
			model.put("rightAlgorithms", algorithms);
			if (errorText != null && errorText.length() > 0) {
				model.put("errorText", errorText);
			}
		}

		return "playground";
	}

	@PostMapping("/playground")
	public String compare(HttpSession httpSession,
								ModelMap model,
								@RequestParam("file")MultipartFile file,
								@RequestParam String firstAlgo,
								@RequestParam String keyLeft,
								@RequestParam String secondAlgo,
								@RequestParam String keyRight) {

		logger.info("Starting comparison of algorithms");
		if (null == firstAlgo ||
				null == file ||
				null == keyLeft ||
				null == secondAlgo ||
				null == keyRight ||
				firstAlgo.length() <= 0 ||
				keyLeft.length() <=0 ||
				secondAlgo.length() <= 0 ||
				keyRight.length() <= 0 ||
				file.isEmpty()) {
			logger.error("Empty Input Parameter(s)");
			return "redirect:/playground?errorText=Empty Input Parameter(s)";
		}
		logger.info("Received parameters: firstAlgo = " + firstAlgo +
				"; keyLeft = " + keyLeft +
				"; secondAlgo = " + secondAlgo +
				"; keyRight = " + keyRight);
		if (firstAlgo.equals(secondAlgo)) {
			logger.error("Same algorithms selected for comparison");
			return "redirect:/playground?errorText=Both algorithms can not be same";
		}
		String fileString = fileToString.getStringFromFile(file);
		comparisonParameters.clearInputParams();
		comparisonParameters.setPlaintext(fileString);
		IEncryptionAlgorithmStrategy firstAlgorithm = algorithmList.getAlgorithmWithName(firstAlgo);
		IEncryptionAlgorithmStrategy secondAlgorithm = algorithmList.getAlgorithmWithName(secondAlgo);
		comparisonParameters.addAlgorithm(firstAlgorithm, keyLeft, firstAlgo);
		comparisonParameters.addAlgorithm(secondAlgorithm, keyRight, secondAlgo);

		if (comparisonResultSet != null) {
			comparisonResultSet.clearResultSet();
		}
		
		comparisonResultSet = compare.compareAlgorithms(comparisonParameters);
		while (comparisonResultSet.hasNextResult()) {
			IComparisonResult result = comparisonResultSet.getNextResult();
			if (result.hasError()) {
				String error = result.getErrorText();
				return "redirect:/playground?errorText=" + error;
			}
		}
		httpSession.setAttribute("resultSet", comparisonResultSet);
		return "redirect:/comparison";
	}
}

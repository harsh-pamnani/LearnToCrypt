package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.AlgorithmFactory;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IAlgorithmFactory;
import com.LearnToCrypt.DAO.AlgorithmDAO;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PlaygroundCountroller implements WebMvcConfigurer {
	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	private AuthenticationManager authenticationManager;
	private ArrayList<String> algorithms;
	private IListAlgorithms algorithmList;
	private IAlgorithmDAO algorithmDAO;
	private IAlgorithmFactory algorithmFactory;

	public PlaygroundCountroller() {
		authenticationManager = AuthenticationManager.instance();
		algorithmDAO = new AlgorithmDAO();
		algorithmFactory = new AlgorithmFactory();
		algorithmList = new ListAlgorithms(algorithmDAO, algorithmFactory);
		algorithms = algorithmList.getAvailableAlogorithms();
	}

	@GetMapping("/playground")
	public String displayPlayground(HttpSession httpSession, ModelMap model) {
		boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
		if(!isUserAuthenticated) {
			return "redirect:/login";
		} else {
			String username = authenticationManager.getUsername(httpSession);
			model.put("username", username);
			model.put("leftAlgorithms", algorithms);
		}
		return "playground";
	}
}

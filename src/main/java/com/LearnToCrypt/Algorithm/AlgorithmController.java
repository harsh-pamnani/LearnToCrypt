package com.LearnToCrypt.Algorithm;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.AlgorithmFactory;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.CaesarCipher;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithm;
import com.LearnToCrypt.BusinessModels.Algorithm;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IAlgorithmDAO;
import com.LearnToCrypt.DAO.IUserDAO;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;


@Controller
public class AlgorithmController implements WebMvcConfigurer {

    AuthenticationManager authenticationManager;
    DAOAbstractFactory abstractFactory;

    String algorithmName;
    String algorithmDescription;
    String algorithmImage;

    public AlgorithmController(){
        authenticationManager = AuthenticationManager.instance();
        abstractFactory = new DAOAbstractFactory();
    }

    @GetMapping("/algorithm")
    public String getAlgorithmPage(HttpSession httpSession,
            @RequestParam(name = "alg", required=false, defaultValue="Algorithm")String alg, Model model){

        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        }

        String username = authenticationManager.getUsername(httpSession);
        model.addAttribute("username", username);
        model.addAttribute("userInput", new UserInput());

        IAlgorithmDAO algorithmDAO = abstractFactory.createAlgorithmDAO();
        Algorithm algorithm = algorithmDAO.getAlgorithm(alg);
        algorithmName = algorithm.getName();
        algorithmDescription = algorithm.getDescription();
        algorithmImage = algorithm.getImage();

        model.addAttribute("alg", algorithmName);
        model.addAttribute("url", "images/"+algorithmImage);
        model.addAttribute("description",algorithmDescription);
        return "algorithm";
    }

    @PostMapping("/algorithm")
    public String submit(HttpSession httpSession, @ModelAttribute UserInput userInput, Model model) {

        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        }

        String username = authenticationManager.getUsername(httpSession);
        model.addAttribute("username", username);
        model.addAttribute("userInput", new UserInput());

        model.addAttribute("alg", algorithmName);
        model.addAttribute("url", "images/"+algorithmImage);
        model.addAttribute("description",algorithmDescription);

        AlgorithmFactory algorithmFactory = new AlgorithmFactory();
        IEncryptionAlgorithm cipher =  algorithmFactory.createAlgorithm(algorithmName);

        cipher.encode(userInput.getKey()+"",userInput.getPlaintext());

        String result = cipher.getResult();
        model.addAttribute("result",result);

        String steps = cipher.getSteps();
        model.addAttribute("steps",steps);

        return "algorithm";
    }

}

package com.LearnToCrypt.Algorithm;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.CaesarCipher;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithm;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.SignIn.AuthenticationManager;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);
    private AuthenticationManager authenticationManager;

    public AlgorithmController(){
        authenticationManager = AuthenticationManager.instance();
    }

    @GetMapping("/algorithm")
    public String getAlgorithmPage(HttpSession httpSession,
            @RequestParam(name = "alg", required=false, defaultValue="Algorithm")
                                               String alg, Model model){

        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        }

        String username = authenticationManager.getUsername(httpSession);
        model.addAttribute("username", username);
        model.addAttribute("userInput", new UserInput());
        model.addAttribute("alg", alg);
        model.addAttribute("url", "images/Caesar_cipher.png");
        return "algorithm";
    }

    @PostMapping("/algorithm")
    public String submit(HttpSession httpSession,@ModelAttribute UserInput userInput, Model model) {

        boolean isUserAuthenticated = authenticationManager.isUserAuthenticated(httpSession);
        if(!isUserAuthenticated) {
            return "redirect:/login";
        }

        model.addAttribute("url", "images/Caesar_cipher.png");

        IEncryptionAlgorithm cipher = new CaesarCipher();

        cipher.encode(userInput.getKey()+"",userInput.getPlaintext());

        String result = cipher.getResult();
        model.addAttribute("result",result);

        String steps = cipher.getSteps();
        model.addAttribute("steps",steps);

        return "algorithm";
    }

}

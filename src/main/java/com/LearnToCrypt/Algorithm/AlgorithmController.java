package com.LearnToCrypt.Algorithm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class AlgorithmController implements WebMvcConfigurer {

    @GetMapping("/algorithm")
    public String getAlgorithmPage(
            @RequestParam(name = "alg", required=false, defaultValue="Algorithm")
                                               String alg, Model model){
        model.addAttribute("userInput", new UserInput());
        model.addAttribute("alg", alg);
        return "algorithm";
    }

    @PostMapping("/algorithm")
    public String submit(@ModelAttribute UserInput userInput) {

        System.out.println(userInput.getKey());
        System.out.println(userInput.getPlaintext());

        return "algorithm";
    }
}

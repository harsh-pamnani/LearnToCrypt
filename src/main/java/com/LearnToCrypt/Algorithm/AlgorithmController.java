package com.LearnToCrypt.Algorithm;

import com.LearnToCrypt.Algorithm.Cipher.CaesarCipher;
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
        model.addAttribute("url", "images/Caesar_cipher.png");
        return "algorithm";
    }

    @PostMapping("/algorithm")
    public String submit(@ModelAttribute UserInput userInput, Model model) {


        System.out.println(userInput.getKey());
        System.out.println(userInput.getPlaintext());

        CaesarCipher caesarCipher = new CaesarCipher();
        caesarCipher.encode(userInput.getKey()+"",userInput.getPlaintext());

        String result = caesarCipher.getResult();
        model.addAttribute("result",result);

        String steps = caesarCipher.getSteps();
        model.addAttribute("steps",steps);

        return "algorithm";
    }
}

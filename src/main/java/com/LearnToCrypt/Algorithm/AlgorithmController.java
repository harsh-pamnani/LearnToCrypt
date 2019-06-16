package com.LearnToCrypt.Algorithm;

import com.LearnToCrypt.Algorithm.Cipher.CaesarCipher;
import com.LearnToCrypt.Algorithm.Cipher.ICipher;
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

        model.addAttribute("url", "images/Caesar_cipher.png");

        ICipher cipher = new CaesarCipher();

        cipher.encode(userInput.getKey()+"",userInput.getPlaintext());

        String result = cipher.getResult();
        model.addAttribute("result",result);

        String steps = cipher.getSteps();
        model.addAttribute("steps",steps);

        return "algorithm";
    }

}

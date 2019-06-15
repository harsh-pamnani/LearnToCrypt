package com.LearnToCrypt.app;

import com.LearnToCrypt.Algorithm.AlgorithmController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.LearnToCrypt.Homepage.HomePageController;
import com.LearnToCrypt.SignUp.SignUpController;
import com.LearnToCrypt.SignUp.SignUpValidatorService;
import com.LearnToCrypt.dashboard.DashboardController;
import com.LearnToCrypt.SignIn.LoginController;
import com.LearnToCrypt.SignIn.LoginValidatorService;


@SpringBootApplication
@ComponentScan(basePackageClasses = {
		HomePageController.class,
		LoginController.class,
		LoginValidatorService.class,
		DashboardController.class,
		SignUpController.class,
		SignUpValidatorService.class,
		AlgorithmController.class
})
public class LearnToCryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnToCryptApplication.class, args);
	}

}
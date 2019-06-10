package com.LearnToCrypt.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.LearnToCrypt.Homepage.HomePageController;
import com.LearnToCrypt.SignUp.SignUpController;
import com.LearnToCrypt.dashboard.DashboardController;
import com.LearnToCrypt.services.LoginValidatorService;
import com.LearnToCrypt.services.SignUpValidatorService;
import com.LearnToCrypt.signin.LoginController;

@SpringBootApplication
@ComponentScan(basePackageClasses = {HomePageController.class, LoginController.class, LoginValidatorService.class, DashboardController.class, SignUpController.class, SignUpValidatorService.class})
public class LearnToCryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnToCryptApplication.class, args);
	}

}
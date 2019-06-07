package com.example.LearnToCrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.Homepage.HomePageController;
import com.example.SignUp.SignUpController;
import com.example.dashboard.DashboardController;
import com.example.services.LoginValidatorService;
import com.example.services.SignUpValidatorService;
import com.exmaple.SignIn.LoginController;

@SpringBootApplication
@ComponentScan(basePackageClasses = {HomePageController.class, LoginController.class, LoginValidatorService.class, DashboardController.class, SignUpController.class, SignUpValidatorService.class})
public class LearnToCryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnToCryptApplication.class, args);
	}

}
package com.LearnToCrypt.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.LearnToCrypt.Homepage.HomePageController;
import com.LearnToCrypt.SignUp.SignUpController;
import com.LearnToCrypt.dashboard.DashboardController;
import com.LearnToCrypt.SignIn.LoginController;

@SpringBootApplication
@ComponentScan(basePackageClasses = {HomePageController.class, LoginController.class, DashboardController.class, SignUpController.class})
public class LearnToCryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnToCryptApplication.class, args);
	}

}
package com.LearnToCrypt.app;

import com.LearnToCrypt.Algorithm.AlgorithmController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.LearnToCrypt.Homepage.HomePageController;
import com.LearnToCrypt.SignIn.LoginController;
import com.LearnToCrypt.SignUp.SignUpController;
import com.LearnToCrypt.Dashboard.DashboardController;


@SpringBootApplication
@ComponentScan(basePackageClasses = {
		HomePageController.class,
		LoginController.class,
		DashboardController.class,
		SignUpController.class,
		AlgorithmController.class
})
//@ComponentScan(basePackageClasses = {HomePageController.class, LoginController.class, DashboardController.class, SignUpController.class})
public class LearnToCryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnToCryptApplication.class, args);
	}

}
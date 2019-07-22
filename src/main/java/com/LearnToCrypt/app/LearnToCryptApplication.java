package com.LearnToCrypt.app;

import com.LearnToCrypt.Algorithm.AlgorithmController;
import com.LearnToCrypt.ForgotPassword.ForgotPasswordController;
import com.LearnToCrypt.MyProgress.MyProgressController;
import com.LearnToCrypt.Playground.ComparisonController;
import com.LearnToCrypt.Playground.PlaygroundController;
import com.LearnToCrypt.Profile.PasswordChangeController;
import com.LearnToCrypt.Profile.ProfileController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.LearnToCrypt.Homepage.HomePageController;
import com.LearnToCrypt.Instructor.ClassManagementController;
import com.LearnToCrypt.Instructor.StudentManagementController;
import com.LearnToCrypt.SignIn.LoginController;
import com.LearnToCrypt.SignUp.SignUpController;
import com.LearnToCrypt.Dashboard.DashboardController;


@SpringBootApplication
@ComponentScan(basePackageClasses = {
		HomePageController.class,
		LoginController.class,
		DashboardController.class,
		SignUpController.class,
		AlgorithmController.class,
		ProfileController.class,
		ForgotPasswordController.class,
		PasswordChangeController.class,
		MyProgressController.class,
		ClassManagementController.class,
		StudentManagementController.class,
		PlaygroundController.class,
		ComparisonController.class
})
public class LearnToCryptApplication {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LearnToCryptApplication.class, args);
		logger.info("Application Start!");
	}

}
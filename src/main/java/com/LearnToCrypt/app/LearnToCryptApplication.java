package com.LearnToCrypt.app;

import com.LearnToCrypt.Algorithm.AlgorithmController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class LearnToCryptApplication {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	public static void main(String[] args) {
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
		logger.fatal("Damn! Fatal error. Please fix me.");
		SpringApplication.run(LearnToCryptApplication.class, args);
	}

}
package com.LearnToCrypt.EmailService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;

import com.LearnToCrypt.ConfigurationLoader.MailConfigLoader;

public class EmailService implements IEmailService {

	private static final Logger logger = LogManager.getLogger(EmailService.class);
	private SimpleMailMessage mailMessage;
	private MailConfigLoader mailConfigLoader;
	private final String fromAddress = "support@LearnToCrypt.com";
	private final String subject = "Password Reset";
	private String smtpHost;
	private int smtpPort;
	private String username;
	private String password;

	public EmailService() {
		logger.info("Creating Email Service Object");
		mailMessage = new SimpleMailMessage();
		mailConfigLoader = MailConfigLoader.getInstance();
		smtpHost = mailConfigLoader.value("host");
		smtpPort = Integer.parseInt(mailConfigLoader.value("port"));
		username = mailConfigLoader.value("username");
		password = mailConfigLoader.value("password");
		logger.info("Created Email Service Object");
	}

	@Async
	public void sendPassResetMail(String email, String url) {
		logger.info("Sending Email to " + email + " with reset url: " + url);
		mailMessage.setFrom(fromAddress);
		mailMessage.setTo(email);
		mailMessage.setSubject(subject);
		mailMessage.setText("Click the link below to reset your password \n" + url);
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(smtpHost);
		mailSender.setPort(smtpPort);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		mailSender.setProtocol("smtp");
		mailSender.setJavaMailProperties(mailConfigLoader.getJavaMailProperties());
		mailSender.send(mailMessage);
		logger.info("Sent Email to " + email + " with reset url: " + url);
	}
}

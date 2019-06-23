package com.LearnToCrypt.EmailService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;

public class EmailService implements IEmailService {

	private SimpleMailMessage mailMessage;
	private final String fromAddress = "support@LearnToCrypt.com";
	private final String subject = "Password Reset";
	private final String smtpHost = null;
	private final int smtpPort = 0;

	public EmailService() {
		mailMessage = new SimpleMailMessage();
	}

	@Async
	public void sendPassResetMail(String email, String url) {

		mailMessage.setFrom(fromAddress);
		mailMessage.setTo(email);
		mailMessage.setSubject(subject);
		mailMessage.setText("Click the link below to reset your password \n" + url);
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(smtpHost);
		mailSender.setPort(smtpPort);
		mailSender.send(mailMessage);
	}
}

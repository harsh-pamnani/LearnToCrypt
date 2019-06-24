package com.LearnToCrypt.EmailService;

public interface IEmailService {
	void sendPassResetMail(String email, String url);
}

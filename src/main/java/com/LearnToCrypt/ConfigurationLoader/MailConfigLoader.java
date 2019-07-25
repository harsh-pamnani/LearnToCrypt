package com.LearnToCrypt.ConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailConfigLoader {
	private static MailConfigLoader mailConfigInstance = null;

	private Map<String, String> mailConfig;
	private static final Logger logger = LogManager.getLogger(MailConfigLoader.class);
	private Properties javaMailProperties;

	public static MailConfigLoader getInstance() {
		if (null == mailConfigInstance) {
			mailConfigInstance = new MailConfigLoader();
		}
		return mailConfigInstance;
	}

	private MailConfigLoader() {
		mailConfig = new HashMap<String, String>();
		javaMailProperties = new Properties();
		loadConfig();
	}

	private void loadConfig() {
		String host = null;
		String port = null;
		String username = null;
		String password = null;
		logger.info("Loading Mail Sender Config");
		try {
			String configFile = "MailSender.properties";
			InputStream inputStream = getClass()
					.getClassLoader()
					.getResourceAsStream(configFile);
			javaMailProperties.load(inputStream);

			host = javaMailProperties.getProperty("host");
			port = javaMailProperties.getProperty("port");
			username = javaMailProperties.getProperty("username");
			password = javaMailProperties.getProperty("password");
		} catch (IOException e) {
			logger.error("Error occurred in accessing the config file:", e);
		}

		mailConfig.put("host", host);
		mailConfig.put("port", port);
		mailConfig.put("username", username);
		mailConfig.put("password", password);
		logger.info("Mail Sender Config loaded successfully");
	}

	public String value(String key) {
		if (mailConfig.containsKey(key)) {
			return mailConfig.get(key);
		}
		return null;
	}

	public Properties getJavaMailProperties() {
		return javaMailProperties;
	}
}

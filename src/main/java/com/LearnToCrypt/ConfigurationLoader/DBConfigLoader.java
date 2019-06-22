package com.LearnToCrypt.ConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.app.LearnToCryptApplication;

public class DBConfigLoader {
	private HashMap<String, String> dbCredentialsMap;

	private static DBConfigLoader configurationUniqueInstance = null;
	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	public static DBConfigLoader instance() {
		if (null == configurationUniqueInstance) {
			configurationUniqueInstance = new DBConfigLoader();
		}
		return configurationUniqueInstance;
	}

	private DBConfigLoader() {
		dbCredentialsMap = new HashMap<String, String>();
		loadConfiguration();
	}

	protected void loadConfiguration() {
		
		String database = null;
		String username = null;
		String password = null;
		String server = null;
		String port = null;
		
		try {
			Properties DatabaseCredentialsProperties = new Properties();
			String configFile = "DatabaseConfigurationTest.properties";
			InputStream input = getClass().getClassLoader().getResourceAsStream(configFile);
			DatabaseCredentialsProperties.load(input);
			
			database = DatabaseCredentialsProperties.getProperty("database");
			username = DatabaseCredentialsProperties.getProperty("username");
			password = DatabaseCredentialsProperties.getProperty("password");
			server = DatabaseCredentialsProperties.getProperty("server");
			port = DatabaseCredentialsProperties.getProperty("port");
			
		} catch (IOException e) {
			logger.error("Error occured in accessing the config file.", e);
		}
		
		
		dbCredentialsMap.put("database", database);
		dbCredentialsMap.put("username", username);
		dbCredentialsMap.put("password", password);
		dbCredentialsMap.put("server", server);
		dbCredentialsMap.put("port", port);
	}

	public String value(String dbConfigurationKey) {
		if (dbCredentialsMap.containsKey(dbConfigurationKey)) {
			return dbCredentialsMap.get(dbConfigurationKey);
		}
		return null;
	}
}

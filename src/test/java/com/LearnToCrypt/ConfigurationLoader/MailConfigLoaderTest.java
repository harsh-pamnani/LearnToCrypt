package com.LearnToCrypt.ConfigurationLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class MailConfigLoaderTest {
	
    private static final Logger logger = LogManager.getLogger(MailConfigLoaderTest.class);

	MailConfigLoader mailConfigLoader;
	String host;
	String port;
	String username;
	String password;
	Properties javaMailProperties;

	public MailConfigLoaderTest() {
		mailConfigLoader = MailConfigLoader.getInstance();

		javaMailProperties = new Properties();

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
			logger.fatal("Error occured in accessing the config file during test case. Error message: ", e.getMessage());
		}
	}

	@Test
	public void testGetHost() {
		assertEquals(host, mailConfigLoader.value("host"));

		assertNotEquals("Wrong Host", mailConfigLoader.value("host"));
	}

	@Test
	public void testGetPort() {
		assertEquals(port, mailConfigLoader.value("port"));

		assertNotEquals("Wrong Port", mailConfigLoader.value("port"));
	}

	@Test
	public void testGetUserName() {
		assertEquals(username, mailConfigLoader.value("username"));

		assertNotEquals("Wrong Username", mailConfigLoader.value("username"));
	}

	@Test
	public void testGetPassword() {
		assertEquals(password, mailConfigLoader.value("password"));

		assertNotEquals("Wrong password", mailConfigLoader.value("password"));
	}

	@Test
	public void testGetJavaMailProperties() {
		assertEquals(javaMailProperties, mailConfigLoader.getJavaMailProperties());

		assertNotEquals(new Properties(), mailConfigLoader.getJavaMailProperties());
	}

}

package com.LearnToCrypt.ConfigurationLoader;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MailConfigLoaderTest {
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
			System.out.println("Error occurred in accessing the config file");
			System.out.println("Error : " + e.getMessage());
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

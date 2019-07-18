package com.LearnToCrypt.ConfigurationLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class DBConfigLoaderTest {

	DBConfigLoader dbConfigLoader;
	String database;
	String username;
	String password;
	String server;
	String port;
	
	public DBConfigLoaderTest() {
		dbConfigLoader = DBConfigLoader.instance();
		
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
			System.out.println("Error occured in accessing the config file");
			System.out.println("Error : " + e.getMessage());
		}
		
	}
	
	@Test
	public void testGetDatabaseName() {
		assertEquals(database, dbConfigLoader.value("database"));
		
		assertNotEquals("Some other database", dbConfigLoader.value("database"));
	}
	
	@Test
	public void testGetUsername() {
		assertEquals(username, dbConfigLoader.value("username"));
		
		assertNotEquals("Fake username", dbConfigLoader.value("username"));
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(password, dbConfigLoader.value("password"));
		
		assertNotEquals("Pass@123", dbConfigLoader.value("password"));
	}
	
	@Test
	public void testGetServer() {
		assertEquals(server, dbConfigLoader.value("server"));
		
		assertNotEquals("XYZ server", dbConfigLoader.value("server"));
	}
	
	@Test
	public void testGetPort() {
		assertEquals(port, dbConfigLoader.value("port"));
		
		assertNotEquals("4547", dbConfigLoader.value("port"));
	}
}
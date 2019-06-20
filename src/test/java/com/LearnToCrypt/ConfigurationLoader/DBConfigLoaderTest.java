package com.LearnToCrypt.ConfigurationLoader;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DBConfigLoaderTest {

	DBConfigLoader dbConfigLoader;
	
	public DBConfigLoaderTest() {
		dbConfigLoader = DBConfigLoader.instance();
	}
	
	@Test
	public void testGetDatabaseName() {
		assertEquals("CSCI5308_7_TEST", dbConfigLoader.value("database"));
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("CSCI5308_7_TEST_USER", dbConfigLoader.value("username"));
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("CSCI5308_7_TEST_7612", dbConfigLoader.value("password"));
	}
	
	@Test
	public void testGetServer() {
		assertEquals("db-5308.cs.dal.ca", dbConfigLoader.value("server"));
	}
	
	@Test
	public void testGetPort() {
		assertEquals("3306", dbConfigLoader.value("port"));
	}
}

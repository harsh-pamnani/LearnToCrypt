package com.LearnToCrypt.DatabaseConnection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

public class DBConnectionTest {
	
	DBConnection dbConnection;
	
	public DBConnectionTest() {
		dbConnection = DBConnection.instance();
	}
	
	@Test
	public void testGetConnection() {
		Connection dbConnect = dbConnection.getConnection();
		assertNotNull(dbConnect);
	}
	
	@Test
	public void testCloseConnection() {
		dbConnection.closeConnection();
		assertTrue(dbConnection.isConnectionClosed());
	}
	
	@Test
	public void testIsConnectionClosed() {
		dbConnection.closeConnection();
		assertTrue(dbConnection.isConnectionClosed());
	}
}
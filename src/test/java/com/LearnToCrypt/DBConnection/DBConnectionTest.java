package com.LearnToCrypt.DBConnection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

import com.LearnToCrypt.DatabaseConnection.DBConnection;

public class DBConnectionTest {
	
	DBConnection dbConnection;
	
	public DBConnectionTest() {
		dbConnection = DBConnection.instance();
	}
	
	
	@Test
	public void getConnectionTest() {
		Connection dbConnect = dbConnection.getConnection();
		assertNotNull(dbConnect);
	}
	
	@Test
	public void closeConnectionTest() {
		assertTrue(dbConnection.closeConnection());
	}
}
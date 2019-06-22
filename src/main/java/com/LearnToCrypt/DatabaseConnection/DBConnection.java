package com.LearnToCrypt.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.LearnToCrypt.ConfigurationLoader.DBConfigLoader;


public class DBConnection {

	private static DBConnection dbConnectionUniqueInstance = null;
	private Connection dbConnection = null;
	private String database;
	private String username;
	private String password;
	private String server;
	private String port;
	private String dbConnectionURL;

	public static DBConnection instance() {
		if (null == dbConnectionUniqueInstance) {
			dbConnectionUniqueInstance = new DBConnection();
		}
		return dbConnectionUniqueInstance;
	}
	
	private DBConnection() {
		DBConfigLoader databaseConfigLoader = DBConfigLoader.instance();
		database = databaseConfigLoader.value("database");
		username = databaseConfigLoader.value("username");
		password = databaseConfigLoader.value("password");
		server = databaseConfigLoader.value("server");
		port = databaseConfigLoader.value("port");
		dbConnectionURL = "jdbc:mysql://" + server + ":" + port + "/" + database;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.dbConnection = DriverManager.getConnection(dbConnectionURL, username, password);
		} catch (Exception e) {
			System.out.println("Some error occured in loading the DB connection");
			System.out.println("Error : " + e.getMessage());
		}
	}

	public Connection getConnection() {
		try {
			if (dbConnection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.dbConnection = DriverManager.getConnection(dbConnectionURL, username, password);
			}
		} catch (Exception e) {
			System.out.println("Some error occured in getting the connection");
			System.out.println("Error : " + e.getMessage());
		}
		return this.dbConnection;
	}

	public static Connection getConnectionForLog() {
		DBConfigLoader databaseConfigLoader = DBConfigLoader.instance();
		String database = databaseConfigLoader.value("database");
		String username = databaseConfigLoader.value("username");
		String password = databaseConfigLoader.value("password");
		String server = databaseConfigLoader.value("server");
		String port = databaseConfigLoader.value("port");
		String dbConnectionURL = "jdbc:mysql://" + server + ":" + port + "/" + database;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(dbConnectionURL, username, password);
		}catch (Exception e) {

		}
		return null;
	}

	public void closeConnection() {
		try {
			if (!dbConnection.isClosed()) {
				dbConnection.close();
			}
		} catch (SQLException e) {
			System.out.println("Some error occured in closing the connection");
			System.out.println("Error : " + e.getMessage());
		}
	}
	
	public boolean isConnectionClosed() {
		boolean isClosed = false;
		try {
			isClosed = dbConnection.isClosed();
		} catch (SQLException e) {
			System.out.println("Some error occured in checking the connection closed.");
			System.out.println("Error : " + e.getMessage());
		}
		
		return isClosed;
	}
}
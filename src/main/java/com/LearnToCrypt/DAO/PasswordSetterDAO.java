package com.LearnToCrypt.DAO;

import com.LearnToCrypt.DatabaseConnection.DBConnection;
import com.LearnToCrypt.HashingAlgorithm.MD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordSetterDAO implements IPasswordSetterDAO {

    DBConnection dbConnectionInstance = null;
    Connection dbConnection = null;
    private PreparedStatement statement;
    ResultSet resultSet;
    MD5 md5Algorithm;

    public PasswordSetterDAO() {
        dbConnectionInstance = DBConnection.instance();
        md5Algorithm = new MD5();
    }

    @Override
    public void setPassword(String email, String password) {
        String hashedPassword = md5Algorithm.generateMD5HashValue(password);
        String query = "CALL update_password(\""+ email +"\", \""+ hashedPassword + ");";
        try {
            dbConnection = dbConnectionInstance.getConnection();
            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        } finally {
            dbConnectionInstance.closeConnection();
        }
    }
}

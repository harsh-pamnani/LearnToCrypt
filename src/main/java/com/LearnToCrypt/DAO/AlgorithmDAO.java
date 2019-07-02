package com.LearnToCrypt.DAO;

import com.LearnToCrypt.BusinessModels.Algorithm;
import com.LearnToCrypt.DatabaseConnection.DBConnection;
import com.LearnToCrypt.app.LearnToCryptApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AlgorithmDAO implements IAlgorithmDAO {

    private DBConnection dbConnectionInstance;
    private Connection dbConnection = null;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

    public AlgorithmDAO() {
        this.dbConnectionInstance = DBConnection.instance();
    }

    @Override
    public Algorithm getAlgorithm(String algorithmName) {
        Algorithm algorithm = new Algorithm();
        String query = "CALL get_algorithm(\"" + algorithmName + "\");";

        try{
            dbConnection = dbConnectionInstance.getConnection();
            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                algorithm.setName(resultSet.getString("name"));
                algorithm.setDescription(resultSet.getString("description"));
                algorithm.setImage(resultSet.getString("image"));
                algorithm.setLevel(resultSet.getInt("level"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in fetching the algorithm.", e);
        }finally {
            dbConnectionInstance.closeConnection();
        }

        return algorithm;
    }
}

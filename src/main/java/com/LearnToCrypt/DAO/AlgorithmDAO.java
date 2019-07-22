package com.LearnToCrypt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.Algorithm;
import com.LearnToCrypt.BusinessModels.BusinessModelAbstractFactory;
import com.LearnToCrypt.BusinessModels.IBusinessModelAbstractFactory;
import com.LearnToCrypt.DatabaseConnection.DBConnection;
import com.LearnToCrypt.app.LearnToCryptApplication;

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

    @Override
    public ArrayList<String> getAllAvailableAlgorithm() {
        String query = "call get_all_avaiable_algorithm();";
        ArrayList<String> result = new ArrayList<>();
        try{
            dbConnection = dbConnectionInstance.getConnection();
            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in fetching the algorithm name.", e);
        }finally {
            dbConnectionInstance.closeConnection();
        }
        return result;
    }

    @Override
    public ArrayList<Algorithm> getAlgorithmByLevelAndClass(int lv,String className) {
        String[] algorithmStrings = getAlgList(className);
        if(algorithmStrings == null){
            logger.info("This student has not register for any class");
            return null;
        }
        ArrayList<String> algorithmList = new ArrayList<>(Arrays.asList(algorithmStrings));
        String query = "call get_algorithm_by_level("+lv+");";
        ArrayList<Algorithm> result = new ArrayList<>();
        IBusinessModelAbstractFactory businessModelAbstractFactory = new BusinessModelAbstractFactory();
        try{
            dbConnection = dbConnectionInstance.getConnection();
            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Algorithm algorithm = businessModelAbstractFactory.createAlgorithm();
                algorithm.setName(resultSet.getString("name"));
                algorithm.setDescriptionShort(resultSet.getString("descriptionShort"));
                algorithm.setType(resultSet.getString("type"));
                if(algorithmList.contains(resultSet.getString("name"))){
                    result.add(algorithm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in fetching the algorithm by id.", e);
        }finally {
            dbConnectionInstance.closeConnection();
        }
        return result;
    }

    @Override
    public String[] getAlgList(String className){
        String[] toReturn = null;
        //get_algorithm_List
        String query = "call get_algorithm_List('"+className+"');";
        try{
            dbConnection = dbConnectionInstance.getConnection();
            statement = dbConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                toReturn  = resultSet.getString(1).split(",");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in fetching the algorithm list.", e);
        }finally {
            dbConnectionInstance.closeConnection();
        }
        return toReturn;
    }
}

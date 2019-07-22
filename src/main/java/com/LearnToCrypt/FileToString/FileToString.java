package com.LearnToCrypt.FileToString;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileToString implements IFileToString{
	private static final Logger logger = LogManager.getLogger(FileToString.class);
	@Override
	public String getStringFromFile(MultipartFile file) {
		try {
			logger.info("Converting file to string");
			StringBuilder builder = new StringBuilder();
			InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(" ");
			}
			logger.info("File read to string successful");
			return builder.toString();
		} catch (IOException e) {
			logger.error("Error Parsing File: " + e.getMessage());
			return null;
		}
	}
}

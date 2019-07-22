package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class Compare implements ICompare {

	private static final Logger logger = LogManager.getLogger(Compare.class);
	private IEncryptionAlgorithm algorithm;
	private IComparisonResultSet resultSet;
	private Calendar calendar;

	public Compare() {
		calendar = Calendar.getInstance();
		resultSet = new ComparisonResultSet();
	}

	@Override
	public IComparisonResultSet compareAlgorithms(IComparisonParameters parameters) {
		IComparisonResult result;
		String name;
		String key;
		String plaintext;
		String encryptedText;
		LocalDateTime startTime;
		LocalDateTime endTime;
		Duration duration;
		logger.info("Starting Comparison");
		while (parameters.hasNextAlgorithmName()) {
			result = new ComparisonResult();
			name = parameters.getNextAlgorithmName();
			key = parameters.getKey(name);
			plaintext = parameters.getPlaintext();
			algorithm = parameters.getEncryptionAlgorithm(name);
			startTime = LocalDateTime.now();
			logger.info("Encrypting with " + name + "; start at: " + startTime);
			encryptedText = algorithm.encode(key, plaintext);
			endTime = LocalDateTime.now();
			logger.info("Encrypting with " + name + "; end at: " + endTime);
			duration = Duration.between(startTime, endTime);
			logger.info("Time taken to encrypt: " + duration.toMillis());
			result.setName(name);
			result.setEncryptedText(encryptedText);
			result.setDuration(duration.toMillis());
			result.setPlaintextLength(plaintext.length());
			result.setEncryptedTextLength(encryptedText.length());
			resultSet.add(result);
		}
		return resultSet;
	}
}

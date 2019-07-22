package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;
import com.LearnToCrypt.Algorithm.UserInput;
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
	private IEncryptionAlgorithmStrategy algorithm;
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
		UserInput input = new UserInput();
		while (parameters.hasNextAlgorithmName()) {
			result = new ComparisonResult();
			name = parameters.getNextAlgorithmName();
			key = parameters.getKey(name);
			plaintext = parameters.getPlaintext();
			algorithm = parameters.getEncryptionAlgorithm(name);
			startTime = LocalDateTime.now();
			logger.info("Encrypting with " + name + "; start at: " + startTime);
			result.setName(name);
			input.setKey(key);
			input.setPlaintext(plaintext);
			String error = algorithm.keyPlainTextValidation(input);
			if (error == null) {
				encryptedText = algorithm.encode(key, plaintext);
				endTime = LocalDateTime.now();
				logger.info("Encrypting with " + name + "; end at: " + endTime);
				duration = Duration.between(startTime, endTime);
				logger.info("Time taken to encrypt: " + duration.toMillis());
				result.setEncryptedText(encryptedText);
				result.setDuration(duration.toMillis());
				result.setPlaintextLength(plaintext.length());
				result.setEncryptedTextLength(encryptedText.length());
				result.setHasError(false);
			} else {
				logger.error("Error in key validation");
				result.setHasError(true);
				result.setErrorText(error);
			}
			resultSet.add(result);
		}
		return resultSet;
	}
}

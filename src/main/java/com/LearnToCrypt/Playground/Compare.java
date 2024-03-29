package com.LearnToCrypt.Playground;

import java.time.Duration;
import java.time.LocalDateTime;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.KeyPlaintextFailureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.Algorithm.UserInput;
import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;

public class Compare implements ICompare {

	private static final Logger logger = LogManager.getLogger(Compare.class);
	private IEncryptionAlgorithmStrategy algorithm;
	private IComparisonResultSet resultSet;

	public Compare() {
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

			try {
				algorithm.keyPlainTextValidation(input);

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
			} catch (KeyPlaintextFailureException e) {
				result.setHasError(true);
				result.setErrorText(e.getMessage());
				logger.error("Error in key validation");
			}

			resultSet.add(result);
		}
		return resultSet;
	}
}

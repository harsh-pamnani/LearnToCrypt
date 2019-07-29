package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;
import com.LearnToCrypt.FileToString.IFileToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.security.InvalidParameterException;

public class ComparisonManager implements IManageComparison {

	private static final Logger logger = LogManager.getLogger(ComparisonManager.class);
	private IComparisonParameters parameters;
	private IListAlgorithms listAlgorithms;
	private IFileToString fileToString;
	private IComparisonResultSet comparisonResultSet;
	private ICompare compare;

	public ComparisonManager(IComparisonParameters parameters,
							 IListAlgorithms algorithms,
							 IFileToString fileToString,
							 ICompare compare) {
		this.parameters = parameters;
		this.listAlgorithms = algorithms;
		this.fileToString = fileToString;
		this.compare = compare;
	}

	@Override
	public void validateAndSetInputParameters(String firstAlgorithm,
											  String secondAlgorithm,
											  String firstKey,
											  String secondKey,
											  MultipartFile file) throws InvalidParameterException {
		logger.info("Validating input parameters");
		if (null == firstAlgorithm ||
				null == file ||
				null == firstKey ||
				null == secondAlgorithm ||
				null == secondKey ||
				firstAlgorithm.length() <= 0 ||
				firstKey.length() <=0 ||
				secondAlgorithm.length() <= 0 ||
				secondKey.length() <= 0 ||
				file.isEmpty()) {

			String error = "Empty Input Parameter(s)";
			logger.error(error);
			throw new InvalidParameterException(error);

		} else {

			if (firstAlgorithm.equals(secondAlgorithm)) {
				String error = "Same algorithms selected for comparison";
				logger.error(error);
				throw new InvalidParameterException(error);
			}

			logger.info("Input validated successfully. Setting comparison parameters");
			String plaintext = fileToString.getStringFromFile(file);
			parameters.clearInputParams();
			parameters.setPlaintext(plaintext);
			IEncryptionAlgorithmStrategy strategy = listAlgorithms.getAlgorithmWithName(firstAlgorithm);
			parameters.addAlgorithm(strategy, firstKey, firstAlgorithm);
			strategy = listAlgorithms.getAlgorithmWithName(secondAlgorithm);
			parameters.addAlgorithm(strategy, secondKey, secondAlgorithm);
		}
	}

	@Override
	public IComparisonResultSet manage() {
		logger.info("Starting Comparison");
		if (null != comparisonResultSet) {
			logger.info("Clearing Result Set");
			comparisonResultSet.clearResultSet();
		}
		comparisonResultSet = compare.compareAlgorithms(parameters);
		return comparisonResultSet;
	}
}

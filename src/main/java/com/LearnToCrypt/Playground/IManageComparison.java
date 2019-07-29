package com.LearnToCrypt.Playground;

import org.springframework.web.multipart.MultipartFile;

import java.security.InvalidParameterException;

public interface IManageComparison {
	void validateAndSetInputParameters(String firstAlgorithm,
								 String secondAlgorithm,
								 String firstKey,
								 String secondKey,
								 MultipartFile file) throws InvalidParameterException;
	IComparisonResultSet manage();
}

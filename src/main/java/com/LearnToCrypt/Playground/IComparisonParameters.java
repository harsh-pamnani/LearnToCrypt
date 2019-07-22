package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;

public interface IComparisonParameters {
	void addAlgorithm(IEncryptionAlgorithmStrategy algorithm, String key, String name);
	boolean hasNextAlgorithmName();
	String getNextAlgorithmName();
	String getKey(String name);
	IEncryptionAlgorithmStrategy getEncryptionAlgorithm(String name);
	void setPlaintext(String plaintext);
	String getPlaintext();
	void clearInputParams();
}

package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithm;

public interface IComparisonParameters {
	void addAlgorithm(IEncryptionAlgorithm algorithm, String key, String name);
	boolean hasNextAlgorithmName();
	String getNextAlgorithmName();
	String getKey(String name);
	IEncryptionAlgorithm getEncryptionAlgorithm(String name);
	void setPlaintext(String plaintext);
	String getPlaintext();
	void clearInputParams();
}

package com.LearnToCrypt.Playground;

public interface IComparisonResult {
	void setName(String name);
	void setEncryptedText(String encryptedText);
	void setDuration(long duration);
	void setPlaintextLength(int length);
	void setEncryptedTextLength(int length);
	String getName();
}

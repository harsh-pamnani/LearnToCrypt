package com.LearnToCrypt.Playground;

public interface IComparisonResult {
	void setName(String name);
	void setEncryptedText(String encryptedText);
	void setDuration(long duration);
	void setPlaintextLength(int length);
	void setEncryptedTextLength(int length);
	void setHasError(boolean hasError);
	void setErrorText(String errorText);
	String getName();
	long getDuration();
	long getPlaintextLength();
	long getEncryptedTextLength();
	String getEncryptedText();
	boolean hasError();
	String getErrorText();
}

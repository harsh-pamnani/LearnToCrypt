package com.LearnToCrypt.Playground;

public class ComparisonResult implements IComparisonResult {

	private String name;
	private String encryptedText;
	private long duration;
	private int plaintextLength;
	private int encryptedTextLength;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setEncryptedText(String encryptedText) {
		this.encryptedText = encryptedText;
	}

	@Override
	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public void setPlaintextLength(int length) {
		this.plaintextLength = length;
	}

	@Override
	public void setEncryptedTextLength(int length) {
		this.encryptedTextLength = length;
	}

	@Override
	public String getName() {
		return name;
	}
}

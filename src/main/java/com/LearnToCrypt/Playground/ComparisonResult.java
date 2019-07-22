package com.LearnToCrypt.Playground;

public class ComparisonResult implements IComparisonResult {

	private String name;
	private String encryptedText;
	private long duration;
	private int plaintextLength;
	private int encryptedTextLength;
	private boolean hasError;
	private String errorText;

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
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	@Override
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public long getDuration() {
		return duration;
	}

	@Override
	public long getPlaintextLength() {
		return plaintextLength;
	}

	@Override
	public long getEncryptedTextLength() {
		return encryptedTextLength;
	}

	@Override
	public String getEncryptedText() {
		return encryptedText;
	}

	@Override
	public boolean hasError() {
		return hasError;
	}

	@Override
	public String getErrorText() {
		return errorText;
	}
}

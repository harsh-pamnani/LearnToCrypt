package com.LearnToCrypt.Playground;

public interface IComparisonResultSet {
	void add(IComparisonResult result);
	boolean hasNextResult();
	IComparisonResult getNextResult();
}

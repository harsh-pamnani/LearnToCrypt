package com.LearnToCrypt.Playground;

import java.util.HashMap;

public class ComparisonResultSet implements IComparisonResultSet {
	private HashMap<String, IComparisonResult> resultMap;

	public ComparisonResultSet() {
		resultMap = new HashMap<>();
	}

	@Override
	public void add(IComparisonResult result) {
		resultMap.put(result.getName(), result);
	}
}

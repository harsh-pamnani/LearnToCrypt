package com.LearnToCrypt.Playground;

import java.util.HashMap;
import java.util.Iterator;

public class ComparisonResultSet implements IComparisonResultSet {
	private HashMap<String, IComparisonResult> resultMap;
	Iterator iterator;

	public ComparisonResultSet() {
		resultMap = new HashMap<>();
		iterator = resultMap.keySet().iterator();
	}

	@Override
	public void add(IComparisonResult result) {
		resultMap.put(result.getName(), result);
		iterator = resultMap.keySet().iterator();
	}

	@Override
	public boolean hasNextResult() {
		if (iterator.hasNext()) {
			return true;
		} else {
			iterator = resultMap.keySet().iterator();
			return false;
		}
	}

	@Override
	public IComparisonResult getNextResult() {
		if (iterator.hasNext()) {
			String nextName = iterator.next().toString();
			return resultMap.get(nextName);
		}
		return null;
	}
}

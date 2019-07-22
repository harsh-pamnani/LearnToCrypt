package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithm;

public interface ICompare {
	IComparisonResultSet compareAlgorithms(IComparisonParameters parameters);
}

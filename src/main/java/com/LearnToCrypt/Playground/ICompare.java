package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;

public interface ICompare {
	IComparisonResultSet compareAlgorithms(IComparisonParameters parameters);
}

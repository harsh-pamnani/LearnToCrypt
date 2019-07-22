package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;

import java.util.ArrayList;

public interface IListAlgorithms {
	ArrayList<String> getAvailableAlgorithms();
	IEncryptionAlgorithmStrategy getAlgorithmWithName(String name);
}

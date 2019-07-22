package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithm;

import java.util.ArrayList;

public interface IListAlgorithms {
	ArrayList<String> getAvailableAlgorithms();
	IEncryptionAlgorithm getAlgorithmWithName(String name);
}

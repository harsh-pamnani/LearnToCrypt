package com.LearnToCrypt.Playground;

import com.LearnToCrypt.Algorithm.EncryptionAlgorithm.IEncryptionAlgorithmStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;

public class ComparisonParameters implements IComparisonParameters{

	private static final Logger logger = LogManager.getLogger(ComparisonParameters.class);
	private HashMap<String, String> keyMap;
	private HashMap<String, IEncryptionAlgorithmStrategy> algorithmMap;
	private Iterator<String> iterator;
	private String plaintext;

	public ComparisonParameters() {
		keyMap = new HashMap<>();
		algorithmMap = new HashMap<>();
		iterator = keyMap.keySet().iterator();
	}

	@Override
	public void addAlgorithm(IEncryptionAlgorithmStrategy algorithm, String key, String name) {
		logger.info("Adding " + name + " to parameters with key :" + key);
		keyMap.put(name, key);
		algorithmMap.put(name, algorithm);
		iterator = keyMap.keySet().iterator();
	}

	@Override
	public boolean hasNextAlgorithmName() {
		if (iterator.hasNext()) {
			return true;
		} else {
			iterator = keyMap.keySet().iterator();
			return false;
		}
	}

	@Override
	public String getNextAlgorithmName() {
		String name = null;
		if (iterator.hasNext()) {
			name = iterator.next().toString();
		}
		return name;
	}

	@Override
	public String getKey(String name) {
		return keyMap.get(name);
	}

	@Override
	public IEncryptionAlgorithmStrategy getEncryptionAlgorithm(String name) {
		return algorithmMap.get(name);
	}

	@Override
	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	@Override
	public String getPlaintext() {
		return plaintext;
	}

	@Override
	public void clearInputParams() {
		keyMap.clear();
		algorithmMap.clear();
		iterator = keyMap.keySet().iterator();
	}
}

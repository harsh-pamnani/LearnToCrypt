package com.LearnToCrypt.BusinessModels;

public interface IBusinessModelAbstractFactory {

	public User createUser();
	public Algorithm createAlgorithm();
	public MyClass createMyClass();
}

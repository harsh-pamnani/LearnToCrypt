package com.LearnToCrypt.BusinessModels;

public class BusinessModelAbstractFactory implements IBusinessModelAbstractFactory {

	public User createUser() {
		return new User();
	}

	@Override
	public Algorithm createAlgorithm() {
		return new Algorithm();
	}

	@Override
	public MyClass createMyClass() {
		return new MyClass();
	}
}

package com.LearnToCrypt.BusinessModels;

public class BusinessModelAbstractFactory implements IBusinessModelAbstractFactory {

	public User createUser() {
		return new User();
	}
}

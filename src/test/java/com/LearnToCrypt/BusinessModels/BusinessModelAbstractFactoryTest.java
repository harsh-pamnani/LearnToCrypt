package com.LearnToCrypt.BusinessModels;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BusinessModelAbstractFactoryTest {
	
	BusinessModelAbstractFactory businessModelAbstractFactory;
	
	public BusinessModelAbstractFactoryTest() {
		businessModelAbstractFactory = new BusinessModelAbstractFactory();
	}
	
	@Test
	public void testCreateUser() {
		assertTrue(businessModelAbstractFactory.createUser() instanceof User);
	}
}

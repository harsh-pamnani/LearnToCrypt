package com.LearnToCrypt.DAO;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.LearnToCrypt.BusinessModels.User;

public class DAOAbstractFactoryTest {
	
	DAOAbstractFactory daoAbstractFactory;
	
	public DAOAbstractFactoryTest() {
		daoAbstractFactory = new DAOAbstractFactory();
	}
	
	@Test
	public void testCreateUser() {
		assertTrue(daoAbstractFactory.createUserDAO() instanceof UserDAO);
		
		assertFalse(daoAbstractFactory.createUserDAO() instanceof User);
	}
}

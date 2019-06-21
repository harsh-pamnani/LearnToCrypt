package com.LearnToCrypt.HashingAlgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class MD5Test {

	@Test
	public void testGenerateMD5HashValue() {
		MD5 md5 = new MD5();
		assertEquals("94d964d0712846285406e818a6adfc77", md5.generateMD5HashValue("Tony@123"));
		
		assertNotEquals("85406e818a6adfc77", md5.generateMD5HashValue("Tony@123"));
	}
}

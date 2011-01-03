package com.armchairfun.poker.dao;

import com.armchairfun.poker.dao.ServerErrorException;

import junit.framework.TestCase;

public class TestServerErrorException extends TestCase {
	

	public void testToXML() throws Exception {
		ServerErrorException e = new ServerErrorException(0, "myErrorMessage");
		String expectedXml = "<error id=\"0\" message=\"myErrorMessage\" />";
		assertEquals(expectedXml, e.toXML());
		
	}

}

package com.armchairfun.poker.dao;

import com.armchairfun.testutils.ArmchairFunTestCase;

public class TestServerErrorException extends ArmchairFunTestCase {
	

	public void testToXML() throws Exception {
		ServerErrorException e = new ServerErrorException(0, "myErrorMessage");
		String expectedXml = "<error id=\"0\" message=\"myErrorMessage\" />";
		assertEquals(expectedXml, e.toXML());
		
	}

}

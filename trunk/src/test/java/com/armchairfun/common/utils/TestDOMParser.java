package com.armchairfun.common.utils;

import junit.framework.TestCase;

import org.xml.sax.SAXParseException;

public class TestDOMParser extends TestCase{

	public void testParseXmlBadXml() {
		try {
			DOMParser.parseXml("<\\gibberish\\");
		} catch (Exception e) {
			assertTrue(e instanceof SAXParseException);
			return;
		}
		fail("Expected SAXParseException when attempting to parse bad xml");
		
	}
}

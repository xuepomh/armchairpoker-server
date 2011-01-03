package com.armchairfun.poker.common.utils;

import junit.framework.TestCase;

import org.xml.sax.SAXParseException;

import com.armchairfun.poker.common.utils.DOMParser;

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

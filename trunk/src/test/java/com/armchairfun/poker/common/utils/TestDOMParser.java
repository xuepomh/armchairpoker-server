package com.armchairfun.poker.common.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import com.armchairfun.testutils.ArmchairFunTestCase;
import com.armchairfun.poker.dao.ServerErrorException;

public class TestDOMParser extends ArmchairFunTestCase {

	public void testParseXmlBadXml() {
		try {
			DOMParser.parseXml("<\\gibberish\\");
		} catch (Exception e) {
			assertTrue(e instanceof SAXParseException);
			return;
		}
		fail("Expected SAXParseException when attempting to parse bad xml");

	}

	public void testGetValueFromDocument() {
		String xml = "<myNode myName=\"testName\"/>";
		try {
			Document doc = DOMParser.parseXml(xml);
			String value = DOMParser.getValueFromDocument(doc, "myNode",
					"myName");
			assertEquals("testName", value);
		} catch (Exception e) {
			fail("Should have been able to parse the xml: " + xml);
		}
	}

	public void testGetValueFromDocumentNullDocument() {
		try {
			String value = DOMParser.getValueFromDocument(null, "myNode",
					"myName");
			assertNull(value);
		} catch (Exception e) {
			fail("getValueFromDocument(..) Should have been able to handle a null document");
		}
	}

	public void testGetValueFromDocumentBadNodeList() {
		String xml = "<myNodes><myNode myName=\"\"/><myNode myName=\"\"/></myNodes>";
		try {
			Document doc = DOMParser.parseXml(xml);
			DOMParser.getValueFromDocument(doc, "myNode", "myName");
			fail("Should have thrown an exception for the bad node list");
		} catch (Exception e) {
			if (!(e instanceof ServerErrorException)) {
				fail("Expected ServerErrorException but got " + e.toString());
			}
		}
	}
}

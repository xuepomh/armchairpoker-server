package com.armchairfun.poker.communication;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.w3c.dom.Document;

import com.armchairfun.poker.common.XMLDescribable;
import com.armchairfun.poker.common.utils.DOMParser;
import com.armchairfun.poker.communication.ServerResponse;

public class TestServerResponse extends TestCase {

	public void testAddResponseItem() {
		List<XMLDescribable> responseItems = new ArrayList<XMLDescribable>();
		responseItems.add(new MockXMLDescribable("testId1"));
		responseItems.add(new MockXMLDescribable("testId2"));
		
		ServerResponse response = new ServerResponse(responseItems);
		assertTrue(response.responseItems.size() == 2);
		response.addResponseItem(new MockXMLDescribable("testId3"));
		assertTrue(response.responseItems.size() == 3);
		
	}
	
	public void testToXML() {
		ServerResponse response = new ServerResponse();
		response.addResponseItem(new MockXMLDescribable("testId3"));
		String xml = response.toXML();
		assertNotNull(xml);
		// check for valid XML syntax
		Document doc;
		try {
			doc = DOMParser.parseXml(xml);
			assertNotNull(doc);
		} catch (Exception e) {
			fail("Exception whilst parsing xml. Exception: " + e.toString());
		}
		
		
	}
}

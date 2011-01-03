package com.armchairfun.common.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DOMParser {

	public static Document parseXml(String xml) throws SAXException, ParserConfigurationException, IOException {
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		xml = xml.trim().replaceFirst("^([\\W]+)<", "<");
		// Using factory get an instance of document builder
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xml));

		// parse using builder to get DOM representation of the XML
		return db.parse(is);

	}

	public static String getValueFromDocument(Document doc, String node,
			String tagName) {

		NodeList nl = doc.getElementsByTagName(node);
		if (nl != null && nl.getLength() == 1) {

			Element el = (Element) nl.item(0);
			return el.getAttribute(tagName);
		}
		return "";

	}

}

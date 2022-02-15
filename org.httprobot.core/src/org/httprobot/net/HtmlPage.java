package org.httprobot.net;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class HtmlPage {

	String url;
	Document document;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public HtmlPage() {

	}
	public HtmlPage(String url, Document document) {
		this.url = url;
		this.document = document;
	}
	public HtmlPage(String url, String xmlString) {
		this(url, convertStringToXMLDocument(xmlString));
	}
	
	public NodeList getByXpath(String expression) {
		//Evaluate XPath against Document itself
		XPath xPath = XPathFactory.newInstance().newXPath();
		try {
			NodeList nodes = (NodeList)xPath.evaluate(expression,
			        document, XPathConstants.NODESET);
			return nodes;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
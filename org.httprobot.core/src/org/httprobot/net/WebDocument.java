package org.httprobot.net;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WebDocument {

	String url;
	Document document;
	WebElement webElement;
	
	public String getUrl() {
		return url;
	}
	public void setRequest(String request) {
		this.url = request;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public WebElement getWebElement() {
		return webElement;
	}
	public void setWebElement(WebElement webElement) {
		this.webElement = webElement;
	}
	public WebDocument() {

	}
	public WebDocument(String request) {
		this.url = request;
	}
	public WebDocument(String request, Document document) {
		this.url = request;
		this.document = document;
	}
	public WebDocument(String request, WebElement documentElement) {
		this(request, convertStringToXMLDocument(documentElement.getAttribute("outerHTML")));
		this.webElement = documentElement;
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
package org.httprobot;

import javax.xml.bind.annotation.XmlElement;

public abstract class AbstractHtml extends AbstractString {

	/**
	 * -6047806638957555356L
	 */
	private static final long serialVersionUID = -6047806638957555356L;

	String XPath;

	@XmlElement
	public String getXPath() {
		return XPath;
	}
	public void setXPath(String xPath) {
		this.XPath = xPath;
	}
	
	public AbstractHtml() {
		super();
	}
}
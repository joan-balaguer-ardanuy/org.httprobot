package org.httprobot.operator.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractHtml;

@XmlRootElement
public final class ContainsElement extends AbstractHtml {

	/**
	 * 7670821045022565695L
	 */
	private static final long serialVersionUID = 7670821045022565695L;

	String XPath;

	@XmlElement
	public String getXPath() {
		return XPath;
	}
	public void setXPath(String xPath) {
		this.XPath = xPath;
	}

	public ContainsElement() {
		super();
	}
}

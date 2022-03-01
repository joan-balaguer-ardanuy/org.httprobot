package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractHtml;
import org.httprobot.event.MessageEventArgs;

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
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		ContainsElement containsElement = ContainsElement.class.cast(e.getSource());
		setXPath(containsElement.getXPath());
	}
}

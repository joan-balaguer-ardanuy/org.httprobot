package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractHtml;
import org.httprobot.placeholder.html.Element;

@XmlRootElement
public final class Html extends AbstractHtml {

	/**
	 * -5625666546941269503L
	 */
	private static final long serialVersionUID = -5625666546941269503L;

	Element element;

	@XmlElement
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}

	public Html() {
		super();
	}
}
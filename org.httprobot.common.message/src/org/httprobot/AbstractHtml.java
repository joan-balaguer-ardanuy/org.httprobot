package org.httprobot;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.operator.html.ContainsElement;
import org.httprobot.operator.html.Element;

public abstract class AbstractHtml extends AbstractString {

	/**
	 * -6047806638957555356L
	 */
	private static final long serialVersionUID = -6047806638957555356L;

	Element element;
	ContainsElement containsElement;
	
	@XmlElement
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	@XmlElement
	public ContainsElement getContainsElement() {
		return containsElement;
	}
	public void setContainsElement(ContainsElement containsElement) {
		this.containsElement = containsElement;
	}
	
	public AbstractHtml() {
		super();
	}
}
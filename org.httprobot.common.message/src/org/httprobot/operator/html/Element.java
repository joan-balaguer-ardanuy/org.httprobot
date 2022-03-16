package org.httprobot.operator.html;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractHtml;

@XmlRootElement
public final class Element extends AbstractHtml {

	/**
	 * -6130911656253160686L
	 */
	private static final long serialVersionUID = -6130911656253160686L;

	Boolean click;
	Boolean save;
	String javaScript;
	Element element;
	ContainsElement containsElement;
	
	@XmlAttribute
	public Boolean getClick() {
		return click;
	}
	public void setClick(Boolean click) {
		this.click = click;
	}
	@XmlAttribute
	public Boolean getSave() {
		return save;
	}
	public void setSave(Boolean store) {
		this.save = store;
	}
	@XmlElement
	public String getJavaScript() {
		return javaScript;
	}
	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}
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
	
	public Element() {
		super();
	}
}

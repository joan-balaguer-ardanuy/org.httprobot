package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractHtml;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Element extends AbstractHtml {

	/**
	 * -6130911656253160686L
	 */
	private static final long serialVersionUID = -6130911656253160686L;
	
	String XPath;
	Boolean click;
	Boolean store;
	String javaScript;
	ContainsElement containsElement;
	Element element;
	
	@XmlElement
	public String getXPath() {
		return XPath;
	}
	public void setXPath(String xPath) {
		this.XPath = xPath;
	}
	@XmlAttribute
	public Boolean getClick() {
		return click;
	}
	public void setClick(Boolean click) {
		this.click = click;
	}
	@XmlAttribute
	public Boolean getStore() {
		return store;
	}
	public void setStore(Boolean store) {
		this.store = store;
	}
	@XmlElement
	public String getJavaScript() {
		return javaScript;
	}
	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}
	@XmlElement
	public ContainsElement getContainsElement() {
		return containsElement;
	}
	public void setContainsElement(ContainsElement containsElement) {
		this.containsElement = containsElement;
	}
	@XmlElement
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	
	public Element() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Element element = Element.class.cast(e.getSource());
		setXPath(element.getXPath());
		setClick(element.getClick());
		setStore(element.getStore());
		setJavaScript(element.getJavaScript());
		setContainsElement(element.getContainsElement());
		setElement(element.getElement());
	}
}

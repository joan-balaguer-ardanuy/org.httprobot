package org.httprobot;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.httprobot.event.MessageEventArgs;
import org.httprobot.placeholder.html.ContainsElement;
import org.httprobot.placeholder.html.Element;

public abstract class AbstractHtml extends AbstractString {

	/**
	 * -6047806638957555356L
	 */
	private static final long serialVersionUID = -6047806638957555356L;

	String id;
	String style;
	String className;
	String title;
	Element element;
	ContainsElement containsElement;
	
	@XmlAttribute
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlAttribute
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	@XmlAttribute
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@XmlAttribute
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	public AbstractHtml() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		AbstractHtml html = AbstractHtml.class.cast(e.getSource());
		setId(html.getId());
		setStyle(html.getStyle());
		setClassName(html.getClassName());
		setTitle(html.getTitle());
		setContainsElement(html.getContainsElement());
		setElement(html.getElement());
	}
}
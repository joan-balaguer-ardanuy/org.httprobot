package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractHtml;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.unit.IsInstance;

@XmlRootElement
public final class Element extends AbstractHtml {

	/**
	 * -6130911656253160686L
	 */
	private static final long serialVersionUID = -6130911656253160686L;
	
	String xPath;
	String nodeName;
	String tagName;
	String id;
	
	Element element;
	ContainsElement containsElement;
	IsInstance isInstance;
	
	@XmlElement
	public String getXPath() {
		return xPath;
	}
	public void setXPath(String xPath) {
		this.xPath = xPath;
	}
	@XmlElement
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	@XmlElement
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	@XmlElement
	public IsInstance getIsInstance() {
		return isInstance;
	}
	public void setIsInstance(IsInstance isInstance) {
		this.isInstance = isInstance;
	}
	
	public Element() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Element element = Element.class.cast(e.getSource());
		setXPath(element.getXPath());
		setNodeName(element.getNodeName());
		setTagName(element.getTagName());
		setId(element.getId());
	}
}

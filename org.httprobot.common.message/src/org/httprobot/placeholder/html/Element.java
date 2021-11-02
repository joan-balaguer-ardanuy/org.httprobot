package org.httprobot.placeholder.html;

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
	
	String xPath;
	String nodeName;
	String tagName;
	String id;
	
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

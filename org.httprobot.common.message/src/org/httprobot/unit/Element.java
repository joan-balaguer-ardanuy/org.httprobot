package org.httprobot.unit;

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
	
	String xPath;
	Boolean click;
	Boolean store;
	String javaScript;
	
	@XmlElement
	public String getXPath() {
		return xPath;
	}
	public void setXPath(String xPath) {
		this.xPath = xPath;
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
	
	public Element() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Element element = Element.class.cast(e.getSource());
		setXPath(element.getXPath());
		setClick(element.getClick());
		setJavaScript(element.getJavaScript());
		
	}
}

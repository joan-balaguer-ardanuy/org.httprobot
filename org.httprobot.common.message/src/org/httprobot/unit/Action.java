package org.httprobot.unit;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.httprobot.Message;
import org.httprobot.operator.html.Element;
import org.httprobot.parameter.Constant;

@XmlRootElement
public final class Action extends Message {

	/**
	 * 798890955203009246L
	 */
	private static final long serialVersionUID = 798890955203009246L;

	Boolean clearQuery;
	String url;
	String method;
	String javaScript;
	Driver driver;
	WebLoader webLoader;
	Element element;
	LinkedHashSet<Constant> constant;
	
	@XmlAttribute
	public Boolean getClearQuery() {
		return clearQuery;
	}
	public void setClearQuery(Boolean clearQuery) {
		this.clearQuery = clearQuery;
	}
	@XmlElement
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@XmlElement
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@XmlElement
	public String getJavaScript() {
		return javaScript;
	}
	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}
	@XmlElement
	public WebLoader getWebLoader() {
		return webLoader;
	}
	public void setWebLoader(WebLoader webLoader) {
		this.webLoader = webLoader;
	}
	@XmlElement
	public LinkedHashSet<Constant> getConstant() {
		return constant;
	}
	public void setConstant(LinkedHashSet<Constant> constant) {
		this.constant = constant;
	}
	@XmlElement
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	@XmlElement
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Action() {
		super();
		constant = new LinkedHashSet<Constant>();
	}
}

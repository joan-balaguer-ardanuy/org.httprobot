package org.httprobot.unit;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.httprobot.AbstractUnit;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.parameter.Constant;

@XmlRootElement
public final class Action extends AbstractUnit {

	/**
	 * 798890955203009246L
	 */
	private static final long serialVersionUID = 798890955203009246L;

	Boolean strictMode;
	String httpAddress;
	String method;
	String javaScript;
	WebLoader webLoader;
	Element element;
	LinkedHashSet<Constant> constant;
	
	@XmlAttribute
	public Boolean getStrictMode() {
		return strictMode;
	}
	public void setStrictMode(Boolean strictMode) {
		this.strictMode = strictMode;
	}
	@XmlElement
	public String getHttpAddress() {
		return httpAddress;
	}
	public void setHttpAddress(String httpAddress) {
		this.httpAddress = httpAddress;
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
	
	public Action() {
		super();
		constant = new LinkedHashSet<Constant>();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Action action = Action.class.cast(e.getSource());
		setStrictMode(action.getStrictMode());
		setHttpAddress(action.getHttpAddress());
		setMethod(action.getMethod());
		setWebLoader(action.getWebLoader());
		setConstant(action.getConstant());
	}
}

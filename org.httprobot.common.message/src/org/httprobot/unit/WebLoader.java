package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.NextPageMethod;
import org.httprobot.Message;

@XmlRootElement
public final class WebLoader extends Message {

	/**
	 * 7700952400328745265L
	 */
	private static final long serialVersionUID = 7700952400328745265L;

	NextPageMethod nextPageMethod;
	Integer time;
	String javaScript;
	String nextPageAttribute;
	String nextPageText;
	String XPath;
	String urlPattern;
	Integer startIndex;
	
	@XmlElement
	public NextPageMethod getNextPageMethod() {
		return nextPageMethod;
	}
	public void setNextPageMethod(NextPageMethod nextPageMethod) {
		this.nextPageMethod = nextPageMethod;
	}
	@XmlElement
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	@XmlElement
	public String getJavaScript() {
		return javaScript;
	}
	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}
	@XmlElement
	public String getNextPageAttribute() {
		return nextPageAttribute;
	}
	public void setNextPageAttribute(String nextPageAttribute) {
		this.nextPageAttribute = nextPageAttribute;
	}
	@XmlElement
	public String getNextPageText() {
		return nextPageText;
	}
	public void setNextPageText(String nextPageText) {
		this.nextPageText = nextPageText;
	}
	@XmlElement
	public String getXPath() {
		return XPath;
	}
	public void setXPath(String xPath) {
		XPath = xPath;
	}
	@XmlElement
	public String getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	@XmlElement
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public WebLoader() {
		
	}
}

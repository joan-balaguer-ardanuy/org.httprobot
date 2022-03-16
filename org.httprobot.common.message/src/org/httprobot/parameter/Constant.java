package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Message;

@XmlRootElement
public final class Constant extends Message
	implements java.util.Map.Entry<String,String> {

	/**
	 * 3549203523654599730L
	 */
	private static final long serialVersionUID = 3549203523654599730L;

	String key;
	String value;
	
	@XmlElement
	public String getKey() {
		return key;
	}
	public String setKey(String key) {
		String oldKey = this.key;
		this.key = key;
		return oldKey;
	}
	@XmlElement
	public String getValue() {
		return value;
	}
	public String setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	public Constant() {
		super();
	}

}

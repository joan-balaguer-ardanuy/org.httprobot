package org.httprobot;

import javax.xml.bind.annotation.XmlElement;

public abstract class Parameter 
	extends XML
		implements java.util.Map.Entry<String,String> {

	/**
	 * -8690414900385495540L
	 */
	private static final long serialVersionUID = -8690414900385495540L;
	
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

	public Parameter() {
		super();
	}
}

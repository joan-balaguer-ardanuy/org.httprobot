package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.AbstractString;

public class EndsWith extends AbstractString {
	
	/**
	 * -1321370241140971639L
	 */
	private static final long serialVersionUID = -1321370241140971639L;
	
	String value;
	
	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public EndsWith() {
		super();
	}
}

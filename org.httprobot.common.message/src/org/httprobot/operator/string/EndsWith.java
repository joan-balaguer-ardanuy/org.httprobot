package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.AbstractString;

/**
 * EndsWith XML message class. The presence of this XML element
 * indicates that the {@link String} being processed will ends with the
 * property value of current instance.
 * @author user
 *
 */
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

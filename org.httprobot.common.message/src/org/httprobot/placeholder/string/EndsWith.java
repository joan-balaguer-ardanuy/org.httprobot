package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.AbstractString;

/**
 * EndsWith XML message class. Inherits {@link AbstractString}.
 * It encapsulates a {@link String} value property.
 * @author joan
 *
 */
public class EndsWith extends AbstractString {
	
	/**
	 * -1321370241140971639L
	 */
	private static final long serialVersionUID = -1321370241140971639L;
	
	/**
	 * The string value property
	 */
	String value;
	
	/**
	 * Returns the {@link String} value property.
	 * @return the {@link String} value property.
	 */
	@XmlElement
	public String getValue() {
		return value;
	}
	/**
	 * Sets the {@link String} value property.
	 * @param value {@link String} the value property
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * {@link EndsWith} default class constructor.
	 */
	public EndsWith() {
		super();
	}
}

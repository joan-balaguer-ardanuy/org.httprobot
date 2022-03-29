package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Contains XML message class. Inherits {@link AbstractString}.
 * It encapsulates a {@link String} value property.
 * @author joan
 *
 */
@XmlRootElement
public final class Contains extends AbstractString {

	/**
	 * -4428551664350568381L
	 */
	private static final long serialVersionUID = -4428551664350568381L;

	/**
	 * The string value property.
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
	 * {@link Contains} default class constructor.
	 */
	public Contains() {
		super();
	}
}
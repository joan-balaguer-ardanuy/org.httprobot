package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Concat XML message class. Inherits {@link AbstractString}.
 * It encapsulates a {@link String} value property.
 * @author joan
 *
 */
@XmlRootElement
public final class Concat extends AbstractString {

	/**
	 * 7159704331152281866L
	 */
	private static final long serialVersionUID = 7159704331152281866L;

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
	 * @param value {@link String} the string value property
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@link Concat} default class constructor.
	 */
	public Concat() {
		super();
	}
}

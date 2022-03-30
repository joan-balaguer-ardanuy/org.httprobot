package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Equals XML message class. Inherits {@link AbstractString}.
 * It encapsulates a {@link String} value property.
 * @author joan
 *
 */
@XmlRootElement
public final class Equals extends AbstractString {

	/**
	 * -6773569233922871483L
	 */
	private static final long serialVersionUID = -6773569233922871483L;

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
	 * @param the {@link String} value property.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@link Equals} default class constructor.
	 */
	public Equals() {
		super();
	}
}
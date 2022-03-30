package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * StartsWith XML message class. Inherits {@link AbstractString}.
 * It encapsulates a {@link String} value property.
 * @author joan
 *
 */
@XmlRootElement
public final class StartsWith extends AbstractString {
	
	/**
	 * -7127204443905401697L
	 */
	private static final long serialVersionUID = -7127204443905401697L;

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
	 * @param value {@link String} the value property.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@link StartsWith} default class constructor.
	 */
	public StartsWith() {
		super();
	}
}

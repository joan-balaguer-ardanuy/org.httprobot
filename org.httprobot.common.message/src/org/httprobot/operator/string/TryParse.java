package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.FieldType;
import org.httprobot.AbstractString;

/**
 * TryParse XML message class. The presence of this XML element
 * indicates that the {@link String} being processed will be parsed
 * as specified {@link FieldType} property 
 * @author user
 *
 */
@XmlRootElement
public final class TryParse extends AbstractString {

	/**
	 * 6864269372865421299L
	 */
	private static final long serialVersionUID = 6864269372865421299L;

	/**
	 * The field type
	 */
	FieldType fieldType;
	
	/**
	 * Returns the {@link FieldType} of the current {@link String} that will be parsed.
	 * @return the {@link FieldType} of the current {@link String} will be parsed.
	 */
	@XmlElement
	public FieldType getFieldType() {
		return fieldType;
	}
	/**
	 * Sets the {@link FieldType} that the current {@link String} being processed will be parsed.
	 * @param fieldType
	 */
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * {@link TryParse} default class constructor.
	 */
	public TryParse() {
		super();
	}
}
package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.FieldType;
import org.httprobot.AbstractOperator;

@XmlRootElement
public final class TryParse extends AbstractOperator {

	/**
	 * 6864269372865421299L
	 */
	private static final long serialVersionUID = 6864269372865421299L;

	FieldType fieldType;
	String value;
	
	@XmlElement
	public FieldType getFieldType() {
		return fieldType;
	}
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public TryParse() {
		super();
	}
}
package org.httprobot.content;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.DataType;
import org.httprobot.XML;

/**
 * Field reference class. The name of this message
 * must match with {@link org.httprobot.datatype.Field}'s name.
 * @author joan
 *
 */
@XmlRootElement
public final class FieldRef extends XML {

	/**
	 * 210911811831590239L
	 */
	private static final long serialVersionUID = 210911811831590239L;
	
	/**
	 * The data type of the field.
	 */
	DataType dataType;
	
	/**
	 * Returns the data type of the field.
	 * @return the data type of the field.
	 */
	@XmlAttribute
	public DataType getDataType() {
		return dataType;
	}
	/**
	 * Sets the data type of the field.
	 * @param dataType the data type of the field.
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * {@link FieldRef} default class constructor.
	 */
	public FieldRef() {
		super();
	}
}
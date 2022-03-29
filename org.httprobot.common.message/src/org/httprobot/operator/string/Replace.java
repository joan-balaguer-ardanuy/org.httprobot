package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Replace XML message class. Inherits {@link AbstractString}.
 * It encapsulates a {@link String} old string property and a {@link String}
 * new string property.
 * 
 * @author joan
 *
 */
@XmlRootElement
public final class Replace extends AbstractString {

	/**
	 * 4097627589074564841L
	 */
	private static final long serialVersionUID = 4097627589074564841L;

	/**
	 * The old string property
	 */
	String oldString;
	/**
	 * The new string property
	 */
	String newString;
	
	/**
	 * Returns the {@link String} old string.
	 * @return the {@link String} old string.
	 */
	@XmlElement
	public String getOldString() {
		return oldString;
	}
	/**
	 * Sets the {@link String} old string.
	 * @param oldString {@link String} the old string
	 */
	public void setOldString(String oldString) {
		this.oldString = oldString;
	}
	
	/**
	 * Returns the {@link String} new string.
	 * @return the {@link String} new string.
	 */
	@XmlElement
	public String getNewString() {
		return newString;
	}
	/**
	 * Sets the {@link String} new string.
	 * @param oldString {@link String} the new string
	 */
	public void setNewString(String newString) {
		this.newString = newString;
	}

	/**
	 * {@link Replace} default class constructor.
	 */
	public Replace() {
		super();
	}
}
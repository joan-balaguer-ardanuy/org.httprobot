package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Substring XML message class. Inherits {@link AbstractString}.
 * It encapsulates an {@link Integer} start index property and an {@link Integer}
 * end index property.
 * 
 * @author joan
 *
 */
@XmlRootElement
public final class Substring extends AbstractString {

	/**
	 * 7723823443728789980L
	 */
	private static final long serialVersionUID = 7723823443728789980L;

	/**
	 * The start index property.
	 */
	Integer startIndex;
	/**
	 * The end index property.
	 */
	Integer endIndex;
	
	/**
	 * Returns the {@link Integer} start index.
	 * @return the {@link Integer} start index.
	 */
	@XmlElement
	public Integer getStartIndex() {
		return startIndex;
	}
	/**
	 * Sets the {@link Integer} start index.
	 * @param startIndex {@link Integer} the start index
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * Returns the {@link Integer} end index.
	 * @return the {@link Integer} end index.
	 */
	@XmlElement
	public Integer getEndIndex() {
		return endIndex;
	}
	/**
	 * Sets the {@link Integer} end index.
	 * @param startIndex {@link Integer} the end index
	 */
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	/**
	 * The {@link Substring} default class constructor.
	 */
	public Substring() {
		super();
	}
}

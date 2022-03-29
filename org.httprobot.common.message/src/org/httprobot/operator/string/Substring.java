package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Substring XML message class. The presence of this XML element
 * indicates that a new {@link String} will be generated from the current
 * string being processed. From StartIndex position to EndIndex position.
 * @author user
 *
 */
@XmlRootElement
public final class Substring extends AbstractString {

	/**
	 * 7723823443728789980L
	 */
	private static final long serialVersionUID = 7723823443728789980L;

	Integer startIndex;
	Integer endIndex;
	
	@XmlElement
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	@XmlElement
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	public Substring() {
		super();
	}
}

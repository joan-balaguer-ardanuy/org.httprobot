package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Equals XML message class. The presence of this XML element
 * indicates that the value property will be equals as
 * current {@link String} being processed.
 * @author user
 *
 */
@XmlRootElement
public final class Equals extends AbstractString {

	/**
	 * -6773569233922871483L
	 */
	private static final long serialVersionUID = -6773569233922871483L;

	String value;
	
	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Equals() {
		super();
	}
}

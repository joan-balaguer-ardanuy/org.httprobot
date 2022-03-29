package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * StartsWith XML message class. The presence of this XML element
 * indicates that the {@link String} being processed will starts with the
 * property value of current instance.
 * @author user
 *
 */
@XmlRootElement
public final class StartsWith extends AbstractString {
	
	/**
	 * -7127204443905401697L
	 */
	private static final long serialVersionUID = -7127204443905401697L;

	String value;
	
	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public StartsWith() {
		super();
	}
}

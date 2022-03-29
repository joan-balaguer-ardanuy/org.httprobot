package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Trim XML message class. The presence of this XML element
 * indicates that the {@link String} being processed will be
 * free of whitespace at the beginning and at the end.
 * @author user
 *
 */
@XmlRootElement
public final class Trim extends AbstractString {

	/**
	 * 7723659999253778536L
	 */
	private static final long serialVersionUID = 7723659999253778536L;

	public Trim() {
		super();
	}
}

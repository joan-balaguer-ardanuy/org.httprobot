package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * ToLowerCase XML message class. The presence of this XML element
 * indicates that the {@link String} being processed will be coded
 * to lower case.
 * @author user
 *
 */
@XmlRootElement
public final class ToLowerCase extends AbstractString {

	/**
	 * 6009104825181173040L
	 */
	private static final long serialVersionUID = 6009104825181173040L;

	public ToLowerCase() {
		super();
	}
}
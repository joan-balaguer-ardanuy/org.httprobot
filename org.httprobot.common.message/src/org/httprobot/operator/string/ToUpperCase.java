package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * ToUpperCase XML message class. The presence of this XML element
 * indicates that the {@link String} being processed will be coded
 * to upper case.
 * @author user
 *
 */
@XmlRootElement
public final class ToUpperCase extends AbstractString {

	/**
	 * -8416932302652715901L
	 */
	private static final long serialVersionUID = -8416932302652715901L;

	public ToUpperCase() {
		super();
	}

}

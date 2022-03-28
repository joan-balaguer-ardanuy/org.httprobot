package org.httprobot.operator;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

/**
 * Url XML message class. The apparition of this message
 * means that the pointer to work must be set in the URL
 * of current analyzing page. The following children
 * must operate on the URL's {@link String} representation.
 * It inherits {@link AbstractString}.
 * 
 * @author joan
 *
 */
@XmlRootElement
public final class Url extends AbstractString {

	/**
	 * 7970525666408855948L
	 */
	private static final long serialVersionUID = 7970525666408855948L;

	/**
	 * {@link Url} default class constructor.
	 */
	public Url() {
		super();
	}
}
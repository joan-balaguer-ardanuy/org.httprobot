package org.httprobot.content;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractContent;

/**
 * Content type reference class. Inherits {@link AbstractContent}.
 * This class makes reference to a {@link ContentType} and 
 * {@link java.util.UUID} must match with the specified content type.
 * @author joan
 *
 */
@XmlRootElement
public final class ContentTypeRef extends AbstractContent {

	/**
	 * -6701067711809325948L
	 */
	private static final long serialVersionUID = -6701067711809325948L;
	
	/**
	 * {@link ContentTypeRef} default class constructor.
	 */
	public ContentTypeRef() {
		super();
	}
}
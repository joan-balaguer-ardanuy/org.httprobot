package org.httprobot.content;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractContent;
import org.httprobot.event.MessageEventArgs;

/**
 * Content type class. Inherits {@link AbstractContent}.
 * It encapsulates {@link LinkedHashSet} of content type reference ({@link ContentTypeRef})
 * and a {@link LinkedHashSet} of field reference ({@link FieldRef}).
 * Content type represents a complex {@link org.apache.solr.common.SolrInputDocument} and each {@link FieldRef}
 * represents an {@link org.apache.solr.common.SolrInputField}. {@link ContentTypeRef} represents another content type.
 * @author joan
 *
 */
@XmlRootElement
public final class ContentType extends AbstractContent {

	/**
	 * 5107630251956423519L
	 */
	private static final long serialVersionUID = 5107630251956423519L;

	/**
	 * A set of content type reference.
	 */
	LinkedHashSet<ContentTypeRef> contentTypeRef;
	/**
	 * A set of field reference.
	 */
	LinkedHashSet<FieldRef> fieldRef;
	
	/**
	 * Returns a {@link LinkedHashSet} of {@link ContentTypeRef}.
	 * @return a {@link LinkedHashSet} of {@link ContentTypeRef}.
	 */
	@XmlElement
	public LinkedHashSet<ContentTypeRef> getContentTypeRef() {
		return contentTypeRef;
	}
	/**
	 * Sets a {@link LinkedHashSet} of {@link ContentTypeRef}.
	 * @param contentTypeRef the {@link LinkedHashSet} of {@link ContentTypeRef}.
	 */
	public void setContentTypeRef(LinkedHashSet<ContentTypeRef> contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	/**
	 * Returns a {@link LinkedHashSet} of {@link FieldRef}.
	 * @return a {@link LinkedHashSet} of {@link FieldRef}.
	 */
	@XmlElement
	public LinkedHashSet<FieldRef> getFieldRef() {
		return fieldRef;
	}
	/**
	 * Sets a {@link LinkedHashSet} of {@link FieldRef}.
	 * @param fieldRef the {@link LinkedHashSet} of {@link FieldRef}.
	 */
	public void setFieldRef(LinkedHashSet<FieldRef> fieldRef) {
		this.fieldRef = fieldRef;
	}
	
	/**
	 * {@link ContentType} default class constructor.
	 */
	public ContentType() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		// Cast source
		ContentType contentType = ContentType.class.cast(e.getSource());
		// Set properties
		setContentTypeRef(contentType.getContentTypeRef());
		setFieldRef(contentType.getFieldRef());
	}
}
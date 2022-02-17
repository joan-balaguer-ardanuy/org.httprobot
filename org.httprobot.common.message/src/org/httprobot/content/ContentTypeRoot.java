package org.httprobot.content;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractContent;
import org.httprobot.event.MessageEventArgs;

/**
 * The root content type class. Inherits {@link AbstractContent}.
 * It encapsulates a {@link LinkedHashSet} of {@link ContentType},
 * a {@link LinkedHashSet} of {@link ContentTypeRef} and a {@link LinkedHashSet}
 * of {@link FieldRef}. It defines the parent future of the 
 * main {@link org.apache.solr.common.SolrInputDocument}
 * @author joan
 *
 */
@XmlRootElement
public final class ContentTypeRoot extends AbstractContent {

	/**
	 * 1632447667657457711L
	 */
	private static final long serialVersionUID = 1632447667657457711L;

	/**
	 * The content type set.
	 */
	LinkedHashSet<ContentType> contentType;
	/**
	 * The content type reference set.
	 */
	LinkedHashSet<ContentTypeRef> contentTypeRef;
	/**
	 * The field reference set.
	 */
	LinkedHashSet<FieldRef> fieldRef;
	
	/**
	 * Returns a {@link LinkedHashSet} of {@link ContentType}.
	 * @return a {@link LinkedHashSet} of {@link ContentType}.
	 */
	@XmlElement
	public LinkedHashSet<ContentType> getContentType() {
		return contentType;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link ContentType}.
	 * @param contentType the {@link LinkedHashSet} of {@link ContentType}.
	 */
	public void setContentType(LinkedHashSet<ContentType> contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * Returns a {@link LinkedHashSet} of {@link ContentTypeRef}.
	 * @return the {@link LinkedHashSet} of {@link ContentTypeRef}.
	 */
	@XmlElement
	public LinkedHashSet<ContentTypeRef> getContentTypeRef() {
		return contentTypeRef;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link ContentTypeRef}.
	 * @param contentTypeRef the {@link LinkedHashSet} of {@link ContentType}.
	 */
	public void setContentTypeRef(LinkedHashSet<ContentTypeRef> contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	
	/**
	 * Returns a {@link LinkedHashSet} of {@link FieldRef}.
	 * @return the {@link LinkedHashSet} of {@link FieldRef}.
	 */
	@XmlElement
	public LinkedHashSet<FieldRef> getFieldRef() {
		return fieldRef;
	}
	/**
	 * Sets ths {@link LinkedHashSet} of {@link FieldRef}.
	 * @param fieldRef {@link LinkedHashSet} of {@link FieldRef}.
	 */
	public void setFieldRef(LinkedHashSet<FieldRef> fieldRef) {
		this.fieldRef = fieldRef;
	}
	
	/**
	 * {@link ContentTypeRoot} default class constructor.
	 */
	public ContentTypeRoot() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		// Cast source
		ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getSource());
		// Set properties
		setContentType(contentTypeRoot.getContentType());
		setContentTypeRef(contentTypeRoot.getContentTypeRef());
		setFieldRef(contentTypeRoot.getFieldRef());
	}
}
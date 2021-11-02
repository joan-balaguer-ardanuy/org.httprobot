package org.httprobot.content;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Content;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class ContentTypeRoot extends Content {

	/**
	 * 1632447667657457711L
	 */
	private static final long serialVersionUID = 1632447667657457711L;

	LinkedHashSet<ContentType> contentType;
	LinkedHashSet<ContentTypeRef> contentTypeRef;
	LinkedHashSet<FieldRef> fieldRef;
	
	@XmlElement
	public LinkedHashSet<ContentType> getContentType() {
		return contentType;
	}
	public void setContentType(LinkedHashSet<ContentType> contentType) {
		this.contentType = contentType;
	}
	
	@XmlElement
	public LinkedHashSet<ContentTypeRef> getContentTypeRef() {
		return contentTypeRef;
	}
	public void setContentTypeRef(LinkedHashSet<ContentTypeRef> contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	
	@XmlElement
	public LinkedHashSet<FieldRef> getFieldRef() {
		return fieldRef;
	}
	public void setFieldRef(LinkedHashSet<FieldRef> fieldRef) {
		this.fieldRef = fieldRef;
	}
	
	public ContentTypeRoot() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getSource());
		setContentType(contentTypeRoot.getContentType());
		setContentTypeRef(contentTypeRoot.getContentTypeRef());
		setFieldRef(contentTypeRoot.getFieldRef());
	}
}
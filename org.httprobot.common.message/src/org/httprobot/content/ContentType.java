package org.httprobot.content;

import java.util.LinkedHashSet;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractContent;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class ContentType extends AbstractContent {

	/**
	 * 5107630251956423519L
	 */
	private static final long serialVersionUID = 5107630251956423519L;

	LinkedHashSet<ContentTypeRef> contentTypeRef;
	LinkedHashSet<FieldRef> fieldRef;
	String name;
	UUID inheritedType;
	
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
	
	@XmlAttribute
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlAttribute
	public UUID getInheritedType() {
		return inheritedType;
	}
	public void setInheritedType(UUID inheritedType) {
		this.inheritedType = inheritedType;
	}
	
	public ContentType() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		ContentType contentType = ContentType.class.cast(e.getSource());
		setContentTypeRef(contentType.getContentTypeRef());
		setFieldRef(contentType.getFieldRef());
		setName(contentType.getName());
		setInheritedType(contentType.getInheritedType());
	}
}

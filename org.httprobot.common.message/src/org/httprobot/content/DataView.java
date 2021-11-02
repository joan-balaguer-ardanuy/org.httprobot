package org.httprobot.content;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Content;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class DataView extends Content {

	/**
	 * 6915946609085950660L
	 */
	private static final long serialVersionUID = 6915946609085950660L;

	ContentTypeRef contentTypeRef;
	String dbSchema;
	LinkedHashSet<FieldRef> fieldRef;
	
	@XmlElement
	public ContentTypeRef getContentTypeRef() {
		return contentTypeRef;
	}
	public void setContentTypeRef(ContentTypeRef contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	
	@XmlAttribute
	public String getDbSchema() {
		return dbSchema;
	}
	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}
	
	@XmlElement
	public LinkedHashSet<FieldRef> getFieldRef() {
		return fieldRef;
	}
	public void setFieldRef(LinkedHashSet<FieldRef> fieldRef) {
		this.fieldRef = fieldRef;
	}

	public DataView() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		DataView dataView = DataView.class.cast(e.getSource());
		setFieldRef(dataView.getFieldRef());
		setDbSchema(dataView.getDbSchema());
		setContentTypeRef(dataView.getContentTypeRef());
	}
}
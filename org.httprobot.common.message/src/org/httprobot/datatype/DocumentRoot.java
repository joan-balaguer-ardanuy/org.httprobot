package org.httprobot.datatype;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Message;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.unit.Action;

@XmlRootElement
public final class DocumentRoot extends Message {

	/**
	 * -5056915889385445752L
	 */
	private static final long serialVersionUID = -5056915889385445752L;

	Action action;
	ContentTypeRef contentTypeRef;
	FieldRoot fieldRoot;
	LinkedHashSet<Document> document;
	
	@XmlElement
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	@XmlElement
	public ContentTypeRef getContentTypeRef() {
		return contentTypeRef;
	}
	public void setContentTypeRef(ContentTypeRef contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	@XmlElement
	public FieldRoot getFieldRoot() {
		return fieldRoot;
	}
	public void setFieldRoot(FieldRoot fieldRoot) {
		this.fieldRoot = fieldRoot;
	}
	@XmlElement
	public LinkedHashSet<Document> getDocument() {
		return document;
	}
	public void setDocument(LinkedHashSet<Document> document) {
		this.document = document;
	}

	public DocumentRoot() {
		super();
		document = new LinkedHashSet<Document>();
	}
}

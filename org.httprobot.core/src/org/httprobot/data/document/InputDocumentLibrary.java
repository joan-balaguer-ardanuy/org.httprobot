package org.httprobot.data.document;

import java.util.UUID;

import org.httprobot.data.field.FieldLibrary;

public class InputDocumentLibrary<K,FieldType>
	extends InputLibrary<K> {

	InputDocument templateDocument;
	FieldLibrary<FieldType> templateFields;
	
	public InputDocumentLibrary() {
		super();
		templateFields = new FieldLibrary<FieldType>();
	}
	public InputDocumentLibrary(InputDocument templateDocument, FieldLibrary<FieldType> templateField) {
		super();
		this.templateFields = templateField;
		this.templateDocument = templateDocument;
	}
	
	public InputDocument getTemplateDocument() {
		return templateDocument.deepInputCopy();
	}
	public FieldLibrary<FieldType> getTemplateFieldLibrary() {
		return templateFields;
	}
	public InputDocument getTemplateChildDocument(UUID contentTypeRef) {
		InputDocument templateDocument = this.templateDocument.findDocumentByUUID(contentTypeRef);

		if (templateDocument != null) {
			return templateDocument.deepInputCopy();
		} else {
			return null;
		}
	}
	@Override
	public InputDocument put(K key, InputDocument value) 
	{		
		return inputDocuments.put(key, value);
	}
}
package org.httprobot.data.document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.FieldRef;
import org.httprobot.data.field.InputField;

public class InputDocument extends SolrInputDocument {

	/**
	 * 873690338978423052L
	 */
	private static final long serialVersionUID = 873690338978423052L;
	
	public static Map<String, SolrInputField> toSolrMap(Map<FieldRef, InputField> fields) {
		Map<String, SolrInputField> solrField = new LinkedHashMap<String, SolrInputField>();

		for (FieldRef fieldRef : fields.keySet()) {
			InputField inputField = fields.get(fieldRef);
			solrField.put(inputField.getName(), inputField);
		}

		return solrField;
	}

	ContentType contentType;
	Map<ContentTypeRef, InputDocument> childInputDocuments;
	Map<FieldRef, InputField> inputFields;
	
	public ContentType getContentType() {
		return contentType;
	}
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
	public Map<ContentTypeRef, InputDocument> getChildInputDocuments() {
		return childInputDocuments;
	}

	public void setChildInputDocuments(Map<ContentTypeRef, InputDocument> childInputDocuments) {
		this.childInputDocuments = childInputDocuments;
	}

	public Map<FieldRef, InputField> getInputFields() {
		return inputFields;
	}

	public void setInputFields(Map<FieldRef, InputField> inputFields) {
		this.inputFields = inputFields;
	}
	
	public InputDocument(ContentType contentType) {
		super();
		this.contentType = contentType;
		childInputDocuments = new LinkedHashMap<ContentTypeRef, InputDocument>();
		inputFields = new LinkedHashMap<FieldRef, InputField>();
	}
	public InputDocument(Map<FieldRef, InputField> inputFields, ContentType contentType) {
		super(toSolrMap(inputFields));
		this.contentType = contentType;
		this.inputFields = inputFields;
		childInputDocuments = new LinkedHashMap<ContentTypeRef, InputDocument>();
	}

	public InputDocument deepInputCopy() {
		Map<FieldRef, InputField> inputFields = new LinkedHashMap<FieldRef, InputField>();

		for (FieldRef fieldRef : inputFields.keySet()) {
			InputField inputField = this.inputFields.get(fieldRef);
			inputFields.put(fieldRef, inputField.deepInputCopy());
		}
		InputDocument clone = new InputDocument(inputFields, this.contentType);
		if (this.getChildDocuments() != null) {
			for (InputDocument child : this.getChildInputDocument()) {
				for (ContentTypeRef contentTypeRef : this.contentType.getContentTypeRef()) {
					if (contentTypeRef.getName().equals(child.getContentType().getName())) {
						clone.addChildDocument(child.deepInputCopy());
						break;
					}
				}
			}
		}
		return clone;
	}
	public void addChildDocument(InputDocument childDocument) {
		for(ContentTypeRef contentTypeRef : contentType.getContentTypeRef()) {
			if(contentTypeRef.getName().equals(childDocument.getContentType().getName())) {
				this.childInputDocuments.put(contentTypeRef, childDocument);
				super.addChildDocument(childDocument);
				return;
			}
		}
	}
	public void addInputDocument(InputDocument inputDocument)
	{
		if (!this.getContentType().getName().equals(inputDocument.getContentType().getName())) {
			Boolean found = false;

			for (ContentTypeRef contentTypeRef : this.contentType.getContentTypeRef()) {
				if (contentTypeRef.getName().equals(inputDocument.getContentType().getName())) {
					this.addChildDocument(inputDocument);
					found = true;
					break;
				}
			}

			if (!found && this.hasChildDocuments()) {
				for (InputDocument inputDoc : this.getChildInputDocument()) {
					inputDoc.addInputDocument(inputDocument);
				}
			}
		} else {
			for (FieldRef fieldRef : this.inputFields.keySet()) {
				if (inputDocument.getInputFields().get(fieldRef) != null) {
					this.put(fieldRef, inputDocument.getInputFields().get(fieldRef));
				}
			}
		}
	}
	public void addInputField(InputField inputField) {
		for(FieldRef fieldRef : inputFields.keySet()) {
			if (this.inputFields.get(fieldRef).equals(inputField)) {
				this.put(fieldRef, inputField);
			}
		}
	}
	public List<InputDocument> getChildInputDocument() {
		List<InputDocument> list = new ArrayList<InputDocument>();

		for (SolrInputDocument solrInputDoc : this.getChildDocuments()) {
			if (solrInputDoc instanceof InputDocument) {
				list.add(InputDocument.class.cast(solrInputDoc));
			}
		}

		return list;
	}
	@Override
	public void clear() {
		if (inputFields != null) {
			// Iterate through field references and remove matching fields
			for (FieldRef fieldRef : inputFields.keySet()) {
				this.inputFields.remove(fieldRef);
			}
		}
		if (this.hasChildDocuments()) {
			// Iterate through content type references and remove matching child documents
			for (ContentTypeRef contentTypeRef : childInputDocuments.keySet()) {
				childInputDocuments.remove(contentTypeRef);
			}
		}
		super.clear();
	}
	public void put(FieldRef key, InputField value) {
		this.put(key.getName(), value);
		for(FieldRef fieldRef : inputFields.keySet()) {
			if(fieldRef.getName().equals(key.getName())) {
				inputFields.put(fieldRef, value);
				return;
			}
		}
	}
	public InputField remove(FieldRef key) {
		InputField inputField = this.inputFields.remove(key);

		if (inputField != null) {
			SolrInputField solrInputField = this.remove(inputField.getName());

			if (solrInputField != null) {
				return inputField;
			}
		}
		return null;
	}
	public void removeChildDocument(InputDocument childInputDocument) {
		if (getChildDocuments().remove(childInputDocument)) {
			for (ContentTypeRef contentTypeRef : childInputDocuments.keySet()) {
				if (contentTypeRef.getName().equals(childInputDocument.getContentType().getName())) {
					childInputDocuments.remove(contentTypeRef);
				}
			}
		}
	}
	public InputDocument findDocumentByUUID(UUID contentTypeRef)
	{
		return this.findDocumentByUUID(this, contentTypeRef);
	}
	private InputDocument findDocumentByUUID(InputDocument currentDocument, UUID contentTypeRefID) {
		if (!contentTypeRefID.equals(this.getContentType().getName())) {
			if (currentDocument.hasChildDocuments()) {
				for (ContentTypeRef contentTypeRef : this.childInputDocuments.keySet()) {
					InputDocument inputDocument = this.childInputDocuments.get(contentTypeRef);

					if (contentTypeRef.getName().equals(contentTypeRefID)) {
						return inputDocument;
					}
				}
			}
		} else {
			return this;
		}
		return null;
	}
}
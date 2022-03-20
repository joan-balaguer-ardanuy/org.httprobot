package org.httprobot.content;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.document.InputDocumentLibrary;
import org.httprobot.data.field.FieldLibrary;
import org.httprobot.data.field.InputField;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class ContentTypeRootParent
	extends ParentEntry<ContentTypeRoot, TemplateLibrary> {

	/**
	 * 1379517986287192515L
	 */
	private static final long serialVersionUID = 1379517986287192515L;
	
	Map<ContentTypeRef, ContentType> contentTypeIndex;
	
	Map<ContentType, ContentTypeParent> contentTypeParents;
	Map<ContentTypeRef, ContentTypeRefParent> contentTypeRefParents;
	Map<FieldRef, FieldRefParent> fieldRefParents;
	
	InputDocumentLibrary<ContentTypeRef, FieldRef> inputDocumentLibrary;
	FieldLibrary<FieldRef> fieldLibrary;

	@Override
	public ContentTypeRoot getKey() {
		return getControl().getMessage();
	}
	@Override
	@XmlElement
	public ContentTypeRootControl getControl() {
		return (ContentTypeRootControl) super.getControl();
	}

	public ContentTypeRootParent() {
		super();
	}
	public ContentTypeRootParent(ContentTypeRoot message, Parent parent) {
		super(message, ContentTypeRootControl.class, parent);
		contentTypeIndex = new LinkedHashMap<ContentTypeRef, ContentType>();
		
		contentTypeParents = new LinkedHashMap<ContentType, ContentTypeParent>();
		contentTypeRefParents = new LinkedHashMap<ContentTypeRef, ContentTypeRefParent>();
		fieldRefParents = new LinkedHashMap<FieldRef, FieldRefParent>();
		
		inputDocumentLibrary = new InputDocumentLibrary<ContentTypeRef, FieldRef>();
		fieldLibrary = new FieldLibrary<FieldRef>();
	}

	@Override
	public TemplateLibrary setValue(TemplateLibrary value) {
		value.putAll(inputDocumentLibrary);
		value.getTemplateFieldLibrary().putAll(fieldLibrary);
		return super.setValue(value);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof FieldRefControl) {
				FieldRefControl fieldRefControl = FieldRefControl.class.cast(e.getSource());
				
				if(fieldRefControl.getParent() instanceof ContentTypeRootControl) {
					FieldRefParent fieldRefManager = new FieldRefParent(fieldRefControl.getMessage(), this);
					addChild(fieldRefManager);
					fieldRefParents.put(fieldRefControl.getMessage(), fieldRefManager);
				}
			} else if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
				
				if(contentTypeRefControl.getParent() instanceof ContentTypeRootControl) {
					ContentTypeRefParent contentTypeRefManager = new ContentTypeRefParent(contentTypeRefControl.getMessage(), this);
					addChild(contentTypeRefManager);
					contentTypeRefParents.put(contentTypeRefControl.getMessage(), contentTypeRefManager);
				}
			} else if(e.getSource() instanceof ContentTypeControl) {
				ContentTypeControl contentTypeControl = ContentTypeControl.class.cast(e.getSource());
				
				if(contentTypeControl.getParent() instanceof ContentTypeRootControl) {
					ContentTypeParent contentTypeManager = new ContentTypeParent(contentTypeControl.getMessage(), this);
					addChild(contentTypeManager);
					contentTypeParents.put(contentTypeControl.getMessage(), contentTypeManager);
				}
			}
			break;
		case PARENT_STARTED:
			if(e.getSource() instanceof ContentTypeRefParent) {
				ContentTypeRefParent contentTypeRefManager = ContentTypeRefParent.class.cast(e.getSource());
				for (ContentType contentType : contentTypeParents.keySet()) {
					if (contentTypeRefManager.getName().equals(contentType.getName())) {
						contentTypeRefManager.setValue(contentType);
						break;
					}
				}
			} else if(e.getSource() instanceof FieldRefParent) {
				// Cast sources
				FieldRefParent fieldRefManager = FieldRefParent.class.cast(e.getSource());
				// Check if manager is a child of current object
				if (fieldRefManager.equals(this.fieldRefParents.get(fieldRefManager.getKey()))) {
					// Initialize new SOLR field
					InputField inputField = new InputField(fieldRefManager.getKey());
					// Set matching message as input data
					fieldRefManager.setValue(inputField);
				}

			} else if(e.getSource() instanceof ContentTypeParent) {
				ContentTypeParent contentTypeManager = ContentTypeParent.class.cast(e.getSource());
				for(ContentType contentType : contentTypeManager) {
					if(contentTypeManager.equals(contentTypeParents.get(contentType))) {
						InputDocument templateDocument = new InputDocument((ContentType) contentTypeManager.getControl().getMessage());
						contentTypeManager.put(contentType, templateDocument);
					}
				}
			}
			break;
		case PARENT_FINISHED:
			if(e.getSource() instanceof ContentTypeRefParent) {
				ContentTypeRefParent contentTypeRefManager  = ContentTypeRefParent.class.cast(e.getSource());
				contentTypeIndex.put(contentTypeRefManager.getKey(), contentTypeRefManager.getValue());
			} else if(e.getSource() instanceof FieldRefParent) {
				FieldRefParent fieldRefManager = FieldRefParent.class.cast(e.getSource());
				// Check only for FieldRef manager children
				if (fieldRefManager.getParent() instanceof ContentTypeRootParent) {
					InputField inputField = fieldRefManager.getValue();
					// Store FieldRef manager output data
					this.fieldLibrary.put(fieldRefManager.getKey(), inputField);
				}
			} else if(e.getSource() instanceof ContentTypeParent) {
				ContentTypeParent contentTypeManager = ContentTypeParent.class.cast(e.getSource());
				if(contentTypeManager.getParent() instanceof ContentTypeRootParent) {
					for(ContentType contentType : contentTypeManager) {
						if(contentTypeManager.equals(contentTypeParents.get(contentType))) {
							InputDocument templateDocument = contentTypeManager.get(contentType);
							for(ContentTypeRef contentTypeRef : contentTypeIndex.keySet()) {
								if(contentTypeRef.getName().equals(contentType.getName())) {
									inputDocumentLibrary.put(contentTypeRef, templateDocument);
								}
							}
						}
					}
				}
			}
		default:
			break;
		}
	}
}
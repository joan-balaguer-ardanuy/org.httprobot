package org.httprobot.content;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.EntryManager;
import org.httprobot.ManagerListener;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.document.InputDocumentLibrary;
import org.httprobot.data.field.FieldLibrary;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

@XmlRootElement
public final class ContentTypeRootManager 
	extends EntryManager<ContentTypeRoot, TemplateLibrary, ContentTypeRootControl> {

	/**
	 * 1379517986287192515L
	 */
	private static final long serialVersionUID = 1379517986287192515L;
	
	Map<ContentTypeRef, ContentType> contentTypeIndex;
	
	Map<ContentType, ContentTypeManager> contentTypeManagers;
	Map<ContentTypeRef, ContentTypeRefManager> contentTypeRefManagers;
	Map<FieldRef, FieldRefManager> fieldRefManagers;
	
	InputDocumentLibrary<ContentTypeRef, FieldRef> inputDocumentLibrary;
	FieldLibrary<FieldRef> fieldLibrary;

	@Override
	@XmlElement
	public ContentTypeRootControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ContentTypeRootControl control) {
		super.setControl(control);
	}

	public ContentTypeRootManager() {
		super();
	}
	public ContentTypeRootManager(ContentTypeRoot message, ManagerListener parent) {
		super(message, ContentTypeRootControl.class, parent);
		contentTypeIndex = new LinkedHashMap<ContentTypeRef, ContentType>();
		
		contentTypeManagers = new LinkedHashMap<ContentType, ContentTypeManager>();
		contentTypeRefManagers = new LinkedHashMap<ContentTypeRef, ContentTypeRefManager>();
		fieldRefManagers = new LinkedHashMap<FieldRef, FieldRefManager>();
		
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
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTENT_TYPE_ROOT_CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeRootControl) {
				ContentTypeRoot contentTypeRoot = ContentTypeRootControl.class.cast(e.getSource()).getMessage();
				setKey(contentTypeRoot);
			}
			break;
		case FIELD_REF_CONTROL_LOADED:
			if(e.getSource() instanceof FieldRefControl) {
				FieldRefControl fieldRefControl = FieldRefControl.class.cast(e.getSource());
				
				if(fieldRefControl.getParent() instanceof ContentTypeRootControl) {
					FieldRefManager fieldRefManager = new FieldRefManager(fieldRefControl.getMessage(), this);
					addChildManager(fieldRefManager);
					fieldRefManagers.put(fieldRefControl.getMessage(), fieldRefManager);
				}
			}
			break;
		case CONTENT_TYPE_REF_CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
				
				if(contentTypeRefControl.getParent() instanceof ContentTypeRootControl) {
					ContentTypeRefManager contentTypeRefManager = new ContentTypeRefManager(contentTypeRefControl.getMessage(), this);
					addChildManager(contentTypeRefManager);
					contentTypeRefManagers.put(contentTypeRefControl.getMessage(), contentTypeRefManager);
				}
			}
			break;
		case CONTENT_TYPE_CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeControl) {
				ContentTypeControl contentTypeControl = ContentTypeControl.class.cast(e.getSource());
				
				if(contentTypeControl.getParent() instanceof ContentTypeRootControl) {
					ContentTypeManager contentTypeManager = new ContentTypeManager(contentTypeControl.getMessage(), this);
					addChildManager(contentTypeManager);
					contentTypeManagers.put(contentTypeControl.getMessage(), contentTypeManager);
				}
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource() instanceof ContentTypeRefManager) {
				ContentTypeRefManager contentTypeRefManager = ContentTypeRefManager.class.cast(e.getSource());
				for (ContentType contentType : contentTypeManagers.keySet()) {
					if (contentTypeRefManager.getUuid().equals(contentType.getUuid())) {
						contentTypeRefManager.setValue(contentType);
						break;
					}
				}
			} else if(e.getSource() instanceof FieldRefManager) {
				// Cast sources
				FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
				// Check if manager is a child of current object
				if (fieldRefManager.equals(this.fieldRefManagers.get(fieldRefManager.getKey()))) {
					// Initialize new SOLR field
					InputField inputField = new InputField(fieldRefManager.getKey());
					// Set matching message as input data
					fieldRefManager.setValue(inputField);
				}

			} else if(e.getSource() instanceof ContentTypeManager) {
				ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(e.getSource());
				for(ContentType contentType : contentTypeManager) {
					if(contentTypeManager.equals(contentTypeManagers.get(contentType))) {
						InputDocument templateDocument = new InputDocument(contentTypeManager.getControl().getMessage());
						contentTypeManager.put(contentType, templateDocument);
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource() instanceof ContentTypeRefManager) {
				ContentTypeRefManager contentTypeRefManager  = ContentTypeRefManager.class.cast(e.getSource());
				contentTypeIndex.put(contentTypeRefManager.getKey(), contentTypeRefManager.getValue());
			} else if(e.getSource() instanceof FieldRefManager) {
				FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
				// Check only for FieldRef manager children
				if (fieldRefManager.getParent() instanceof ContentTypeRootManager) {
					InputField inputField = fieldRefManager.getValue();
					// Store FieldRef manager output data
					this.fieldLibrary.put(fieldRefManager.getKey(), inputField);
				}
			} else if(e.getSource() instanceof ContentTypeManager) {
				ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(e.getSource());
				if(contentTypeManager.getParent() instanceof ContentTypeRootManager) {
					for(ContentType contentType : contentTypeManager) {
						if(contentTypeManager.equals(contentTypeManagers.get(contentType))) {
							InputDocument templateDocument = contentTypeManager.get(contentType);
							for(ContentTypeRef contentTypeRef : contentTypeIndex.keySet()) {
								if(contentTypeRef.getUuid().equals(contentType.getUuid())) {
									inputDocumentLibrary.put(contentTypeRef, templateDocument);
								}
							}
						}
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
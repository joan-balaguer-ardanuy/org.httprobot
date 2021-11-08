package org.httprobot.content;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Enums.ManagerEventType;
import org.httprobot.Manager;
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
	extends Manager<ContentTypeRootControl>
		implements java.util.Map.Entry<ContentTypeRoot, TemplateLibrary> {

	/**
	 * 1379517986287192515L
	 */
	private static final long serialVersionUID = 1379517986287192515L;

	TemplateLibrary value;
	
	Map<ContentTypeRef, ContentType> contentTypeIndex;
	
	Map<ContentType, ContentTypeManager> contentTypeManagers;
	Map<ContentTypeRef, ContentTypeRefManager> contentTypeRefManagers;
	Map<FieldRef, FieldRefManager> fieldRefManagers;
	
	InputDocumentLibrary<ContentTypeRef, FieldRef> inputDocumentLibrary;
	FieldLibrary<FieldRef> fieldLibrary;

	@Override
	public ContentTypeRoot getKey() {
		return getControl().getMessage();
	}
	@Override
	public TemplateLibrary getValue() {
		return value;
	}
	@Override
	public TemplateLibrary setValue(TemplateLibrary value) {
		TemplateLibrary oldValue = this.value;
		value.putAll(inputDocumentLibrary);
		value.getTemplateFieldLibrary().putAll(fieldLibrary);
		this.value = value;
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		return oldValue;
	}
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
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
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
				for(ContentType contentType : contentTypeManagers.keySet()) {
					if(contentTypeRefManager.getUuid().equals(contentType.getUuid())) {
						contentTypeRefManager.setValue(contentType);
						break;
					}
				}
			} else if(e.getSource() instanceof FieldRefManager) {
				FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
				if(fieldRefManager.equals(fieldRefManagers.get(fieldRefManager.getKey()))) {
					InputField inputField = new InputField(fieldRefManager.getKey());
					fieldRefManager.setValue(inputField);
				}
			} else if(e.getSource() instanceof ContentTypeManager) {
				ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(e.getSource());
				for(ContentType contentType : contentTypeManager) {
					if(contentTypeManager.equals(contentTypeManagers.get(contentType))) {
						contentTypeManager.put(contentType, null);
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource() instanceof ContentTypeRefManager) {
				ContentTypeRefManager contentTypeRefManager  = ContentTypeRefManager.class.cast(e.getSource());
				ContentType contentType = contentTypeRefManager.getValue();
				contentTypeIndex.put(contentTypeRefManager.getKey(), contentType);
			} else if(e.getSource() instanceof FieldRefManager) {
				FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
				if(fieldRefManager.getParent() instanceof ContentTypeRootManager) {
					fieldLibrary.put(fieldRefManager.getKey(), fieldRefManager.getValue());
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
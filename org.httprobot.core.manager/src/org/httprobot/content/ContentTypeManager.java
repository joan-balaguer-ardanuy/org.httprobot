package org.httprobot.content;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ManagerListener;
import org.httprobot.MappingManager;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

@XmlRootElement
public class ContentTypeManager
	extends MappingManager<ContentType, InputDocument, ContentTypeControl> {

	/**
	 * 7504962006510123725L
	 */
	private static final long serialVersionUID = 7504962006510123725L;
	
	Map<ContentTypeRef, ContentTypeManager> contentTypeManagers;
	Map<ContentTypeRef, ContentTypeRefManager> contentTypeRefManagers;
	Map<FieldRef, FieldRefManager> fieldRefManagers;
	
	InputDocument templateDocument;
	Integer contentTypeRefCount;
	
	public ContentTypeManager() {
		super();
	}
	public ContentTypeManager(ContentType message, ManagerListener parent) {
		super(message, ContentTypeControl.class, parent);
		contentTypeRefCount = 0;
		contentTypeManagers = new LinkedHashMap<ContentTypeRef, ContentTypeManager>();
		contentTypeRefManagers = new LinkedHashMap<ContentTypeRef, ContentTypeRefManager>();
		fieldRefManagers = new LinkedHashMap<FieldRef, FieldRefManager>();
	}
	@Override
	public InputDocument put(ContentType key, InputDocument value) {
		if(getUuid().equals(key.getUuid()) && value == null) {
			value = templateDocument.deepInputCopy();
			setKey(key);
			setValue(value);
			return super.put(key, value);
		}
		return null;
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTENT_TYPE_REF_CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
				ContentTypeRefManager contentTypeRefManager = new ContentTypeRefManager(contentTypeRef, this);
				addChildManager(contentTypeRefManager);
				contentTypeRefManagers.put(contentTypeRef, contentTypeRefManager);
			}
			break;
		case FIELD_REF_CONTROL_LOADED:
			if(e.getSource() instanceof FieldRefControl) {
				FieldRef fieldRef = FieldRefControl.class.cast(e.getSource()).getMessage();
				FieldRefManager fieldRefManager = new FieldRefManager(fieldRef, this);
				addChildManager(fieldRefManager);
				fieldRefManagers.put(fieldRef, fieldRefManager);
			}
			break;
		case CONTENT_TYPE_CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeControl) {
				keySet().add(ContentTypeControl.class.cast(e.getSource()).getMessage());
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
			if(e.getSource() instanceof FieldRefManager) {
				FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
				if(fieldRefManager.equals(fieldRefManagers.get(fieldRefManager.getKey()))) {
					InputField inputField = new InputField(fieldRefManager.getKey());
					fieldRefManager.setValue(inputField);
				}
			} else if(e.getSource() instanceof ContentTypeRefManager) {
				ContentTypeRefManager contentTypeRefManager = ContentTypeRefManager.class.cast(e.getSource());
				if(contentTypeRefManagers.containsValue(contentTypeRefManager)) {
					for(ContentType contentType : getContentTypeRoot().getContentType()) {
						contentTypeRefManager.setValue(contentType);
					}
				}
			} else if(e.getSource() instanceof ContentTypeManager) {
				ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(e.getSource());
				for(ContentTypeRef contentTypeRef : contentTypeManagers.keySet()) {
					if(contentTypeManager.equals(contentTypeManagers.get(contentTypeRef))) {
						ContentTypeRefManager contentTypeRefManager = contentTypeRefManagers.get(contentTypeRef);
						ContentType contentType = contentTypeRefManager.getValue();
						InputDocument templateDocument = new InputDocument(contentType);
						contentTypeManager.put(contentType, templateDocument);
						return;
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource() instanceof FieldRefManager) {
				FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
				templateDocument.put(fieldRefManager.getKey(), fieldRefManager.getValue());
			} else if(e.getSource() instanceof ContentTypeRefManager) {
				ContentTypeRefManager contentTypeRefManager = ContentTypeRefManager.class.cast(e.getSource());
				for(ContentTypeRef key : contentTypeRefManagers.keySet()) {
					if(contentTypeRefManager.equals(contentTypeRefManagers.get(key))) {
						ContentType contentType = contentTypeRefManager.getValue();
						ContentTypeManager contentTypeManager = new ContentTypeManager(contentType, this);
						contentTypeManagers.put(key, contentTypeManager);
						//contentTypeManager.start();
						
						contentTypeRefCount++;
						if(contentTypeRefCount == contentTypeRefManagers.size() - 1) {
							for(ContentTypeRef contentTypeRef : contentTypeManagers.keySet()) {
								ContentTypeManager value = contentTypeManagers.get(contentTypeRef);
								value.start();
							}
						}
						break;
					}
				}
			} else if(e.getSource() instanceof ContentTypeManager) {
				ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(e.getSource());
				for(ContentTypeRef contentTypeRef : contentTypeManagers.keySet()) {
					if(contentTypeManager.equals(contentTypeManagers.get(contentTypeRef))) {
						for(ContentType contentType : contentTypeManager.keySet()) {
							templateDocument.addChildDocument(contentTypeManager.get(contentType));
						}
						return;
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
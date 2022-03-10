package org.httprobot.content;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Listener;
import org.httprobot.Entry;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

@XmlRootElement
public class ContentTypeParent
	extends Entry<ContentType, InputDocument, ContentTypeControl> {

	/**
	 * 7504962006510123725L
	 */
	private static final long serialVersionUID = 7504962006510123725L;
	
	Map<ContentTypeRef, ContentTypeParent> contentTypeParents;
	Map<ContentTypeRef, ContentTypeRefParent> contentTypeRefParents;
	Map<FieldRef, FieldRefParent> fieldRefParents;
	
	InputDocument templateDocument;
	Integer contentTypeRefCount;
	
	public ContentTypeParent() {
		super();
	}
	public ContentTypeParent(ContentType message, Listener parent) {
		super(message, ContentTypeControl.class, parent);
		contentTypeRefCount = 0;
		contentTypeParents = new LinkedHashMap<ContentTypeRef, ContentTypeParent>();
		contentTypeRefParents = new LinkedHashMap<ContentTypeRef, ContentTypeRefParent>();
		fieldRefParents = new LinkedHashMap<FieldRef, FieldRefParent>();
	}
	@Override
	public InputDocument put(ContentType key, InputDocument value) {
		value = templateDocument.deepInputCopy();
		setKey(key);
		setValue(value);
		return super.put(key, value);
	}
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
				ContentTypeRefParent contentTypeRefManager = new ContentTypeRefParent(contentTypeRef, this);
				addChildManager(contentTypeRefManager);
				contentTypeRefParents.put(contentTypeRef, contentTypeRefManager);
			} else if(e.getSource() instanceof FieldRefControl) {
				FieldRef fieldRef = FieldRefControl.class.cast(e.getSource()).getMessage();
				FieldRefParent fieldRefManager = new FieldRefParent(fieldRef, this);
				addChildManager(fieldRefManager);
				fieldRefParents.put(fieldRef, fieldRefManager);
			} else if(e.getSource() instanceof ContentTypeControl) {
				keySet().add(ContentTypeControl.class.cast(e.getSource()).getMessage());
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource() instanceof FieldRefParent) {
				FieldRefParent fieldRefManager = FieldRefParent.class.cast(e.getSource());
				if(fieldRefManager.equals(fieldRefParents.get(fieldRefManager.getKey()))) {
					InputField inputField = new InputField(fieldRefManager.getKey());
					fieldRefManager.setValue(inputField);
				}
			} else if(e.getSource() instanceof ContentTypeRefParent) {
				ContentTypeRefParent contentTypeRefManager = ContentTypeRefParent.class.cast(e.getSource());
				if(contentTypeRefParents.containsValue(contentTypeRefManager)) {
					for(ContentType contentType : getContentTypeRoot().getContentType()) {
						contentTypeRefManager.setValue(contentType);
					}
				}
			} else if(e.getSource() instanceof ContentTypeParent) {
				ContentTypeParent contentTypeManager = ContentTypeParent.class.cast(e.getSource());
				for(ContentTypeRef contentTypeRef : contentTypeParents.keySet()) {
					if(contentTypeManager.equals(contentTypeParents.get(contentTypeRef))) {
						ContentTypeRefParent contentTypeRefManager = contentTypeRefParents.get(contentTypeRef);
						ContentType contentType = contentTypeRefManager.getValue();
						InputDocument templateDocument = new InputDocument(contentType);
						contentTypeManager.put(contentType, templateDocument);
						return;
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource() instanceof FieldRefParent) {
				FieldRefParent fieldRefManager = FieldRefParent.class.cast(e.getSource());
				templateDocument.put(fieldRefManager.getKey(), fieldRefManager.getValue());
			} else if(e.getSource() instanceof ContentTypeRefParent) {
				ContentTypeRefParent contentTypeRefManager = ContentTypeRefParent.class.cast(e.getSource());
				for(ContentTypeRef key : contentTypeRefParents.keySet()) {
					if(contentTypeRefManager.equals(contentTypeRefParents.get(key))) {
						ContentType contentType = contentTypeRefManager.getValue();
						ContentTypeParent contentTypeManager = new ContentTypeParent(contentType, this);
						contentTypeParents.put(key, contentTypeManager);
						contentTypeManager.start();
						
						contentTypeRefCount++;
						if(contentTypeRefCount == contentTypeRefParents.size() - 1) {
							for(ContentTypeRef contentTypeRef : contentTypeParents.keySet()) {
								ContentTypeParent value = contentTypeParents.get(contentTypeRef);
								value.start();
							}
						}
						break;
					}
				}
			} else if(e.getSource() instanceof ContentTypeParent) {
				ContentTypeParent contentTypeManager = ContentTypeParent.class.cast(e.getSource());
				for(ContentTypeRef contentTypeRef : contentTypeParents.keySet()) {
					if(contentTypeManager.equals(contentTypeParents.get(contentTypeRef))) {
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
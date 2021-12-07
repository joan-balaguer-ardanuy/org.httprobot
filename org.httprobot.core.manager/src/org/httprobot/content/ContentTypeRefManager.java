package org.httprobot.content;

import org.httprobot.ManagerListener;
import org.httprobot.MapManager;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

public class ContentTypeRefManager 
	extends MapManager<ContentTypeRef, ContentType, ContentTypeRefControl> {

	/**
	 * 2966040272596645359L
	 */
	private static final long serialVersionUID = 2966040272596645359L;
	
	public ContentTypeRefManager() {
		super();
	}
	public ContentTypeRefManager(ContentTypeRef message, ManagerListener parent) {
		super(message, ContentTypeRefControl.class, parent);
	}
	
	@Override
	public ContentType put(ContentTypeRef key, ContentType value) {
		if(keySet().contains(key)) {
			if(value.getUuid().equals(key.getUuid())) {
				return super.put(key, value);		
			}
		}
		return null;
	}
	
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTENT_TYPE_REF_CONTROL_LOADED:
			if (e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
				keySet().add(contentTypeRef);
			}
			break;

		default:
			break;
		}
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
package org.httprobot.content;

import org.httprobot.ManagerListener;
import org.httprobot.MappingManager;
import org.httprobot.event.ManagerEventArgs;

public class ContentTypeRefManager 
	extends MappingManager<ContentTypeRef, ContentType, ContentTypeRefControl> {

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
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
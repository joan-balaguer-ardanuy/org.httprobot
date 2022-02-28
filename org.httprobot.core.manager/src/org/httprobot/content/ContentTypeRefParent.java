package org.httprobot.content;

import org.httprobot.ManagerListener;
import org.httprobot.MappingParent;
import org.httprobot.event.ManagerEventArgs;

public class ContentTypeRefParent 
	extends MappingParent<ContentTypeRef, ContentType, ContentTypeRefControl> {

	/**
	 * 2966040272596645359L
	 */
	private static final long serialVersionUID = 2966040272596645359L;
	
	public ContentTypeRefParent() {
		super();
	}
	public ContentTypeRefParent(ContentTypeRef message, ManagerListener parent) {
		super(message, ContentTypeRefControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
package org.httprobot.content;

import org.httprobot.Listener;
import org.httprobot.ParentMapping;
import org.httprobot.event.ManagerEventArgs;

public class ContentTypeRefParent 
	extends ParentMapping<ContentTypeRef, ContentType, ContentTypeRefControl> {

	/**
	 * 2966040272596645359L
	 */
	private static final long serialVersionUID = 2966040272596645359L;
	
	public ContentTypeRefParent() {
		super();
	}
	public ContentTypeRefParent(ContentTypeRef message, Listener parent) {
		super(message, ContentTypeRefControl.class, parent);
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}
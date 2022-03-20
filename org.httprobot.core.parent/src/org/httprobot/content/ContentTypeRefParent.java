package org.httprobot.content;

import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.event.EventArgs;

public class ContentTypeRefParent 
	extends ParentEntry<ContentTypeRef, ContentType> {

	/**
	 * 2966040272596645359L
	 */
	private static final long serialVersionUID = 2966040272596645359L;
	
	public ContentTypeRefParent() {
		super();
	}
	public ContentTypeRefParent(ContentTypeRef message, Parent parent) {
		super(message, ContentTypeRefControl.class, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
	}
}
package org.httprobot.content;

import org.httprobot.Manager;
import org.httprobot.ManagerListener;
import org.httprobot.Enums.ManagerEventType;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

public class ContentTypeRefManager 
	extends Manager<ContentTypeRefControl> 
		implements java.util.Map.Entry<ContentTypeRef, ContentType> {

	/**
	 * 2966040272596645359L
	 */
	private static final long serialVersionUID = 2966040272596645359L;

	ContentType value;
	
	@Override
	public ContentTypeRef getKey() {
		return getControl().getMessage();
	}
	@Override
	public ContentType getValue() {
		return value;
	}
	@Override
	public ContentType setValue(ContentType value) {
		ContentType oldValue = this.value;
		this.value = value;
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		return oldValue;
	}
	
	public ContentTypeRefManager() {
		super();
	}
	public ContentTypeRefManager(ContentTypeRef message, ManagerListener parent) {
		super(message, ContentTypeRefControl.class, parent);
	}
	
	@Override
	public void OnCommandReceived(CommandEventArgs e) {

	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
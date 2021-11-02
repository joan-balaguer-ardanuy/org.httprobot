package org.httprobot.event;

import org.httprobot.Enums.EventType;
import org.httprobot.Enums.ManagerEventType;

public class ManagerEventArgs extends EventArgs {

	/**
	 * -3456430476072153580L
	 */
	private static final long serialVersionUID = -3456430476072153580L;

	ManagerEventType managerEventType;
	Object value;
	
	public ManagerEventType getManagerEventType() {
		return managerEventType;
	}
	public Object getValue() {
		return value;
	}
	public ManagerEventArgs(Object source, ManagerEventType managerEventType) {
		super(source, EventType.MANAGER);
		this.managerEventType = managerEventType;
	}
	public ManagerEventArgs(Object source, Object value, ManagerEventType managerEventType) {
		super(source, EventType.MANAGER);
		this.managerEventType = managerEventType;
		this.value = value;
	}
}
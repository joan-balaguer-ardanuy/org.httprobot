package org.httprobot.event;

import java.util.EventObject;

import org.httprobot.Enums.EventType;

public abstract class EventArgs extends EventObject {

	/**
	 * 6347247597829991161L
	 */
	private static final long serialVersionUID = 6347247597829991161L;
	
	EventType eventType;
	
	public EventType getEventType() {
		return eventType;
	}

	public EventArgs(Object source, EventType eventType) {
		super(source);
		this.eventType = eventType;
	}
}

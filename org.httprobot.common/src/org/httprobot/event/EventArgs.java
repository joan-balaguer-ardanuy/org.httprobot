package org.httprobot.event;

import java.util.EventObject;

import org.httprobot.EventType;

/**
 * Event arguments class.
 * Each fired event will have an argument that inherits this class.
 * 
 * @author joan
 *
 */
public class EventArgs extends EventObject {

	/**
	 * 6347247597829991161L
	 */
	private static final long serialVersionUID = 6347247597829991161L;
	
	/**
	 * The event type
	 */
	EventType eventType;
	
	/**
	 * Returns the event type.
	 * @return the event type.
	 */
	public EventType getEventType() {
		return eventType;
	}

	Object value;
	
	public Object getValue() {
		return value;
	}
	
	/**
	 * {@link EventArgs} default class constructor.
	 * @param source {@link Object} the source of the event
	 * @param eventType {@link EventType} the event type
	 */
	public EventArgs(Object source, EventType eventType) {
		super(source);
		this.eventType = eventType;
	}
	/**
	 * {@link EventArgs} default class constructor.
	 * @param source {@link Object} the source of the event
	 * @param eventType {@link EventType} the event type
	 */
	public EventArgs(Object source, Object value, EventType eventType) {
		super(source);
		this.value = value;
		this.eventType = eventType;
	}
}
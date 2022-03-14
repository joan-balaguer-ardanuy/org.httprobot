package org.httprobot;

import java.util.LinkedHashSet;
import java.util.Set;

import org.httprobot.event.EventArgs;

/**
 * Test
 * @author joan
 *
 */
public abstract class AbstractEventListener
	extends XML
		implements EventListener {

	/**
	 * 2951712424683872158L
	 */
	private static final long serialVersionUID = 2951712424683872158L;
	
	/**
	 * The event listeners set.
	 */
	Set<EventListener> eventListeners;
	
	/**
	 * {@link AbstractEventListener} default class constructor.
	 */
	public AbstractEventListener() {
		super();
		this.eventListeners = new LinkedHashSet<EventListener>();
	}
	/**
	 * {@link AbstractEventListener} class constructor.
	 * @param name {@link String} the name of the XML message
	 */
	public AbstractEventListener(String name) {
		super(name);
		this.eventListeners = new LinkedHashSet<EventListener>();
	}
	
	/**
	 * Adds new event listener to the event listeners set.
	 * @param listener {@link EventListener} the new listener to be added.
	 */
	public final void addEventListener(EventListener listener) {
		eventListeners.add(listener);
	}
	/**
	 * Removes old event listener from the event listeners set.
	 * @param listener {@link EventListener} the old listener to be removed.
	 */
	public final void removeEventListener(EventListener listener) {
		eventListeners.remove(listener);
	}
	/**
	 * Fires event to all {@link EventListener} added in the set.
	 * @param e {@link EventArgs} the arguments of the event
	 */
	protected void SendEvent(EventArgs e) {
		for(EventListener listener : eventListeners) {
			// send event to all event listeners
			listener.OnEventReceived(e);
		}
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		// stream event
		SendEvent(e);		
	}
}
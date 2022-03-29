package org.httprobot;

import org.httprobot.event.EventArgs;

/**
 * Event listener interface.
 * It is a {@link Message} and is {@link java.util.EventListener}.
 * All implementing instances will execute a parent event.
 * @author joan
 *
 */
public interface EventListener
	extends java.util.EventListener, Message {
	
	/**
	 * Fired when event is received.
	 * @param e {@link EventArgs} the arguments of the event.
	 */
	void OnEventReceived(EventArgs e);
}
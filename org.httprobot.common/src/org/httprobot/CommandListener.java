package org.httprobot;

import java.io.Serializable;
import java.util.EventListener;

import org.httprobot.event.CommandEventArgs;

/**
 * Command listener interface.
 * It is {@link EventListener} and {@link Serializable}.
 * All implementing instances will execute a command event.
 * @author joan
 *
 */
public interface CommandListener extends EventListener, Serializable {
	
	/**
	 * Fired when command event is received.
	 * @param e {@link CommandEventArgs} the arguments of the command event.
	 */
	void OnCommandReceived(CommandEventArgs e);
}
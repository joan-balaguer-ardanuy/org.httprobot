package org.httprobot;

import org.httprobot.event.ControlEventArgs;

/**
 * XML message control listener interface. 
 * {@link XML} controls implement it when reading XML messages. 
 * It is {@link MessageListener}.
 * @author joan
 *
 */
public interface ControlListener extends MessageListener {
	
	/**
	 * Triggered when XML message is initialized.
	 * @param e {@link ControlEventArgs} the XML message control event arguments.
	 */
	public void OnControlInitialized(ControlEventArgs e);

	/**
	 * Triggered when XML message is loaded.
	 * @param e {@link ControlEventArgs} the XML message control event arguments.
	 */
	public void OnControlLoaded(ControlEventArgs e);
}

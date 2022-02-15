package org.httprobot;

import java.util.UUID;

import org.httprobot.event.MessageEventArgs;

/**
 * Message listener interface.
 * It's {@link CommandListener}. All XML framework messages will implement this interface.
 * Each instance will have an {@link UUID}. And will fire an event when message is
 * marshalled and unmarshalled by JAXB framework.
 * 
 * 
 * @author joan
 *
 */
public interface Message extends CommandListener {

	/**
	 * Returns the universal unique ID of the message.
	 * @return {@link UUID} the universal unique ID of the message.
	 */
	UUID getUuid();
	
	/** Sets the universal unique ID of the message.
	 * @param uuid {@link UUID} the universal unique ID of the message to be set.
	 */
	void setUuid(UUID uuid);
	
	/**
	 * Fired when {@link javax.xml.bind.Marshaller} has marshalled current message.
	 * @param e {@link MessageEventArgs} the arguments of this event.
	 */
	void OnMessageMarshalled(MessageEventArgs e);

	/**
	 * Fired when {@link javax.xml.bind.Unmarshaller} has unmarshalled current message.
	 * @param e {@link MessageEventArgs} the arguments of this event.
	 */
	void OnMessageUnmarshalled(MessageEventArgs e);
}

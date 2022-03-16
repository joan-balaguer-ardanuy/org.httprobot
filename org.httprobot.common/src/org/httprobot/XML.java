package org.httprobot;

import java.io.Serializable;

/**
 * XML interface.
 * It's {@link Serializable}. All XML framework messages will 
 * implement this interface. Each instance will have a name.
 * 
 * @author joan
 */
public interface XML extends Serializable {
	
	/**
	 * Returns bane of the message.
	 * @return {@link String} the name of the message.
	 */
	String getName();
	
	/** Sets the name of the message.
	 * @param name {@link String} the name of the message to be set.
	 */
	void setName(String name);
}

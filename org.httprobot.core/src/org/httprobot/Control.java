package org.httprobot;

/**
 * XML message control interface. All XML message control must implement this interface.
 * It's a {@link Listener} of Control, and is also a {@link DataMapping} of Data-Object entries.
 * @author joan
 *
 */
public interface Control 
	extends Listener<Control>, DataMapping<Data,Object> {
	
	/**
	 * Returns the message being controlled.
	 * @return the message being controlled.
	 */
	XML getMessage();
	/**
	 * Initialitzation event fired when instance is being instanced.
	 */
	void initialize();
	/**
	 * Loads the XML data into a {@link java.util.Map}
	 */
	void load();
}
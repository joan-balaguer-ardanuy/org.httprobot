/**
 * 
 */
package org.httprobot.common.events;

import java.util.UUID;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.MarshallerEventType;
import org.httprobot.common.rml.Rml;

/**
 * @author Joan
 *
 */
public class MarshallerEventArgs extends EventArgs 
{

	/**
	 * -8034279773067879775L
	 */
	private static final long serialVersionUID = -8034279773067879775L;

	private MarshallerEventType met;
	private UUID uuid;
	private Rml rml;
	
	/**
	 * Gets the Marshaller event type.
	 * @return {@link MarshallerEventType}
	 */
	public MarshallerEventType getMarshallerEventType()
	{
		return met;
	}
	/**
	 * @return the RML object
	 */
	public Rml getRml()
	{
		return this.rml;
	}
	
	public UUID getUUID()
	{
		return this.uuid;
	}
	/**
	 * MarshallerEventArgs class constructor.
	 * @param value {@link Object} the value to send
	 * @param et {@link MarshallerEventType} the Marshaller event type.
	 */
	public MarshallerEventArgs(Object value, MarshallerEventType met) 
	{
		super(value, EventType.RML_MARSHALLER);
		this.rml = Rml.class.cast(value);
		this.uuid = this.rml.getUuid();
		this.met = met;
	}
}
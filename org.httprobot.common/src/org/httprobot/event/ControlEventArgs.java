package org.httprobot.event;

import org.httprobot.ControlEventType;
import org.httprobot.EventType;
import org.httprobot.Message;

public class ControlEventArgs extends EventArgs {

	/**
	 * 4204262239190271773L
	 */
	private static final long serialVersionUID = 4204262239190271773L;
	
	ControlEventType controlEventType;
	Message message;
	
	public ControlEventType getControlEventType() {
		return controlEventType;
	}
	
	public Message getMessage() {
		return message;
	}

	public ControlEventArgs(Object source, Message message, ControlEventType controlEventType) {
		super(source, EventType.CONTROL);
		this.controlEventType = controlEventType;
		this.message = message;
	}

}

package org.httprobot.event;

import org.httprobot.Enums.ControlEventType;
import org.httprobot.Enums.EventType;
import org.httprobot.MessageListener;

public class ControlEventArgs extends EventArgs {

	/**
	 * 4204262239190271773L
	 */
	private static final long serialVersionUID = 4204262239190271773L;
	
	ControlEventType controlEventType;
	MessageListener message;
	
	public ControlEventType getControlEventType() {
		return controlEventType;
	}
	
	public MessageListener getMessage() {
		return message;
	}

	public ControlEventArgs(Object source, MessageListener message, ControlEventType controlEventType) {
		super(source, EventType.CONTROL);
		this.controlEventType = controlEventType;
		this.message = message;
	}

}

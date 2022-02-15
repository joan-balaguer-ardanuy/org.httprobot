package org.httprobot.event;

import org.httprobot.EventType;
import org.httprobot.WebServiceEventType;
import org.httprobot.Message;

public class WebServiceEventArgs extends EventArgs {

	/**
	 * 7710813033701492909L
	 */
	private static final long serialVersionUID = 7710813033701492909L;
	private	Message message;
	private WebServiceEventType webServiceEventType;
	
	public WebServiceEventType getWebServiceEventType() {
		return webServiceEventType;
	}
	public Message getMessage() {
		return message;
	}
	public WebServiceEventArgs(Object source, Message message, WebServiceEventType webServiceEventType) {
		super(source, EventType.NET);
		this.message = message;
	}
}
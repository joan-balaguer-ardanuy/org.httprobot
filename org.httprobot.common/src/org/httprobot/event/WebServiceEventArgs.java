package org.httprobot.event;

import org.httprobot.Enums.EventType;
import org.httprobot.Enums.WebServiceEventType;
import org.httprobot.MessageListener;

public class WebServiceEventArgs extends EventArgs {

	/**
	 * 7710813033701492909L
	 */
	private static final long serialVersionUID = 7710813033701492909L;
	private	MessageListener message;
	private WebServiceEventType webServiceEventType;
	
	public WebServiceEventType getWebServiceEventType() {
		return webServiceEventType;
	}
	public MessageListener getMessage() {
		return message;
	}
	public WebServiceEventArgs(Object source, MessageListener message, WebServiceEventType webServiceEventType) {
		super(source, EventType.NET);
		this.message = message;
	}
}
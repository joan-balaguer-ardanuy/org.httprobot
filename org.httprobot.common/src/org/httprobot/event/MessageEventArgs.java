package org.httprobot.event;

import org.httprobot.Enums.EventType;
import org.httprobot.Enums.XmlEventType;
import org.httprobot.MessageListener;

public class MessageEventArgs extends EventArgs {

	/**
	 * 4527510445873196733L
	 */
	private static final long serialVersionUID = 4527510445873196733L;

	XmlEventType xmlEventType;
	
	public XmlEventType getXmlEventType() {
		return xmlEventType;
	}

	public MessageEventArgs(MessageListener source, XmlEventType eventType) {
		super(source, EventType.XML);
		this.xmlEventType = eventType;
	}

}

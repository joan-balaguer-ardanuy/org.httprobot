package org.httprobot.event;

import org.httprobot.EventType;
import org.httprobot.XmlEventType;
import org.httprobot.Message;

public class MessageEventArgs extends EventArgs {

	/**
	 * 4527510445873196733L
	 */
	private static final long serialVersionUID = 4527510445873196733L;

	XmlEventType xmlEventType;
	
	public XmlEventType getXmlEventType() {
		return xmlEventType;
	}

	public MessageEventArgs(Message source, XmlEventType eventType) {
		super(source, EventType.XML);
		this.xmlEventType = eventType;
	}

}

package org.httprobot.content;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.Message;
import org.httprobot.event.EventArgs;

/**
 * {@link ContentTypeRef} {@link Message} {@link Control} class.
 * Inherits {@link AbstractControl}.
 * @author joan
 *
 */
@XmlRootElement
public final class ContentTypeRefControl 
	extends AbstractControl {

	/**
	 * 740303867514973622L
	 */
	private static final long serialVersionUID = 740303867514973622L;
	
	@Override
	@XmlElement
	public ContentTypeRef getMessage() {
		return (ContentTypeRef) super.getMessage();
	}
	
	/**
	 * {@link ContentTypeRefControl} default class constructor.
	 */
	public ContentTypeRefControl() {
		super();
	}
	/**
	 * {@link ContentTypeRefControl} class constructor.
	 * @param message {@link ContentTypeRef} the message
	 * @param parent {@link Control} the parent instance
	 */
	public ContentTypeRefControl(ContentTypeRef message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				ContentTypeRef contentTypeRef = (ContentTypeRef) e.getValue();
				// check required data
				if (contentTypeRef.getName() == null || contentTypeRef.getName() == null) {
					throw new Error("ContentTypeRefControl.OnEventReceived: name cannot be null.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				ContentTypeRef contentTypeRef = (ContentTypeRef) e.getValue();
				put(Data.NAME, contentTypeRef.getName());
			}
			break;
		default:
			break;
		}
	}
}

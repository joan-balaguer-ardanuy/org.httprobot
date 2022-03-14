package org.httprobot.content;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class ContentTypeRefControl 
	extends AbstractControl<ContentTypeRef> {

	/**
	 * 740303867514973622L
	 */
	private static final long serialVersionUID = 740303867514973622L;
	
	@Override
	@XmlElement
	public ContentTypeRef getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(ContentTypeRef message) {
		super.setMessage(message);
	}
	
	public ContentTypeRefControl() {
		setMessage(new ContentTypeRef());
	}
	public ContentTypeRefControl(ContentTypeRef message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getValue());
				// Cbeck required data
				if (contentTypeRef.getName() == null || contentTypeRef.getName() == null) {
					throw new Error("ContentTypeRefControl.OnControlInitialized: UUID cannot be null.");
				}
			}	
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getValue());

				if (contentTypeRef.getName() != null) {
					put(Data.NAME, contentTypeRef.getName());
				} else {
					throw new Error("ContentTypeRefControl.OnControlLoaded: UUID cannot be null.");
				}
			}
			break;
		default:
			break;
		}
	}
}

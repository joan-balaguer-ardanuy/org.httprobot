package org.httprobot.content;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

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
	public void OnControlInitialized(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());
			// Cbeck required data
			if (contentTypeRef.getName() == null || contentTypeRef.getName() == null) {
				throw new Error("ContentTypeRefControl.OnControlInitialized: UUID cannot be null.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());

			if (contentTypeRef.getName() != null) {
				put(Data.NAME, contentTypeRef.getName());
				// Send event to parent
				SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
			} else {
				throw new Error("ContentTypeRefControl.OnControlLoaded: UUID cannot be null.");
			}
		}
	}
}

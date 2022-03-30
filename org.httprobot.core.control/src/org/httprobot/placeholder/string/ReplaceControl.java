package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.string.Replace;

@XmlRootElement
public final class ReplaceControl
	extends AbstractStringControl {

	/**
	 * 7760167926576384464L
	 */
	private static final long serialVersionUID = 7760167926576384464L;

	@Override
	@XmlElement
	public Replace getMessage() {
		return (Replace) super.getMessage();
	}
	
	public ReplaceControl() {
		super();
	}
	public ReplaceControl(Replace message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				Replace replace = Replace.class.cast(e.getValue());
				if(replace.getOldString() == null) {
					throw new Error("ReplaceControl.OnEventReceived: OldString XML element message is missing.");
				}
				if(replace.getNewString() == null) {
					throw new Error("ReplaceControl.OnEventReceived: NewString XML element message is missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {

				Replace replace = Replace.class.cast(e.getValue());

				if (replace.getOldString() != null) {
					put(Data.OLD_STRING, replace.getOldString());
				} else {
					throw new Error("ReplaceControl.OnEventReceived: OldString XML message element expected.");
				}
				if (replace.getNewString() != null) {
					put(Data.NEW_STRING, replace.getNewString());
				} else {
					throw new Error("ReplaceControl.OnEventReceived: NewString XML message element expected.");
				}
			}
			break;
		default:
			break;
		}
	}
}
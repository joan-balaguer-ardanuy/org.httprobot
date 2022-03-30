package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.string.ToLowerCase;

@XmlRootElement
public final class ToLowerCaseControl
	extends AbstractStringControl {

	/**
	 * -8140876774202456900L
	 */
	private static final long serialVersionUID = -8140876774202456900L;
	
	@Override
	@XmlElement
	public ToLowerCase getMessage() {
		return (ToLowerCase) super.getMessage();
	}
	
	public ToLowerCaseControl() {
		super();
	}
	public ToLowerCaseControl(ToLowerCase message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				ToLowerCase.class.cast(e.getValue());
			}
			break;
		case CONTROL_LOADED:
			break;
		default:
			break;
		}
	}
}

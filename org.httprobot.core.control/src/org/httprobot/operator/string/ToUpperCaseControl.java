package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class ToUpperCaseControl
	extends AbstractStringControl {

	/**
	 * -8536577675929221898L
	 */
	private static final long serialVersionUID = -8536577675929221898L;
	
	@Override
	@XmlElement
	public ToUpperCase getMessage() {
		return (ToUpperCase) super.getMessage();
	}
	
	public ToUpperCaseControl() {
		super();
	}
	public ToUpperCaseControl(ToUpperCase message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				ToUpperCase.class.cast(e.getValue());
			}
			break;
		case CONTROL_LOADED:
			break;
		default:
			break;
		}
	}
}
package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.string.EndsWith;

@XmlRootElement
public final class EndsWithControl
	extends AbstractStringControl {

	/**
	 * 3494988225577418938L
	 */
	private static final long serialVersionUID = 3494988225577418938L;
	
	@Override
	@XmlElement
	public EndsWith getMessage() {
		return (EndsWith) super.getMessage();
	}
	
	public EndsWithControl() {
		super();
	}
	public EndsWithControl(EndsWith message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {

				EndsWith endsWith = EndsWith.class.cast(e.getValue());

				if (endsWith.getValue() == null) {
					throw new Error("EndsWithControl.OnEventReceived: value XML element message is missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {

				EndsWith endsWith = EndsWith.class.cast(e.getValue());
				
				put(Data.VALUE, endsWith.getValue());
			}
			break;
		default:
			break;
		}
	}
}

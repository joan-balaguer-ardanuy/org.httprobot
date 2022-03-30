package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.string.StartsWith;

@XmlRootElement
public final class StartsWithControl 
	extends AbstractStringControl {

	/**
	 * 6170464494921818052L
	 */
	private static final long serialVersionUID = 6170464494921818052L;
	
	@Override
	@XmlElement
	public StartsWith getMessage() {
		return (StartsWith) super.getMessage();
	}
	
	public StartsWithControl() {
		super();
	}
	public StartsWithControl(StartsWith message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {

				StartsWith startsWith = StartsWith.class.cast(e.getValue());

				if (startsWith.getValue() == null) {
					throw new Error("StartsWithControl.OnEventReceived: value XML element message is missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {

				StartsWith startsWith = StartsWith.class.cast(e.getValue());
				
				put(Data.VALUE, startsWith.getValue());
			}
			break;
		default:
			break;
		}
	}
}

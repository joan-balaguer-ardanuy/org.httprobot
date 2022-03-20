package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class ContainsControl 
	extends AbstractStringControl {

	/**
	 * -5379321827008140153L
	 */
	private static final long serialVersionUID = -5379321827008140153L;
	
	@Override
	@XmlElement
	public Contains getMessage() {
		return (Contains) super.getMessage();
	}
	
	public ContainsControl() {
		super();
	}
	public ContainsControl(Contains message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {

				Contains contains = Contains.class.cast(e.getValue());

				if (contains.getValue() == null) {
					throw new Error("ContainsControl.OnEventReceived: value XML element message is missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {

				Contains contains = Contains.class.cast(e.getValue());
				
				put(Data.VALUE, contains.getValue());
			}
			break;
		default:
			break;
		}
	}
}
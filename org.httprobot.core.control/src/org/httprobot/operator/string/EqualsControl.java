package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class EqualsControl
	extends AbstractStringControl<Equals>{

	/**
	 * -9117585827351033039L
	 */
	private static final long serialVersionUID = -9117585827351033039L;

	@Override
	@XmlElement
	public Equals getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Equals message) {
		super.setMessage(message);
	}
	
	public EqualsControl() {
		super();
	}
	public EqualsControl(Equals message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				Equals equals = Equals.class.cast(e.getValue());
				
				if(equals.getValue() == null) {
					throw new Error("EqualsControl.OnEventReceived: value XML element message.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {

				Equals equals = Equals.class.cast(e.getValue());

				put(Data.VALUE, equals.getValue());
			}
			break;
		default:
			break;
		}
	}
}
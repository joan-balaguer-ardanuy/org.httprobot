package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class ConcatControl
	extends AbstractStringControl<Concat> {

	/**
	 * 4853020141248338937L
	 */
	private static final long serialVersionUID = 4853020141248338937L;

	@Override
	@XmlElement
	public Concat getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Concat message) {
		super.setMessage(message);
	}
	
	public ConcatControl() {
		super();
	}
	public ConcatControl(Concat message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				Concat split = Concat.class.cast(e.getValue());
				
				if(split.getValue() == null) {
					throw new Error("ConcatControl.OnEventReceived: XML Concat message value missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				Concat concat = Concat.class.cast(e.getSource());
				// save value
				put(Data.VALUE, concat.getValue());
			}
			break;
		default:
			break;
		}
	}
}
package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

/**
 * {@link Concat} message {@link Control}.
 * Inherits {@link AbstractStringControl}.
 * @author joan
 *
 */
@XmlRootElement
public final class ConcatControl
	extends AbstractStringControl {

	/**
	 * 4853020141248338937L
	 */
	private static final long serialVersionUID = 4853020141248338937L;

	@Override
	@XmlElement
	public Concat getMessage() {
		return (Concat) super.getMessage();
	}
	
	/**
	 * {@link ConcatControl} default class constructor.
	 */
	public ConcatControl() {
		super();
	}
	/**
	 * {@link ConcatControl} class constructor.
	 * @param message {@link Concat} the message
	 * @param parent {@link Control} the parent instance
	 */
	public ConcatControl(Concat message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				// cast source's value
				Concat concat = (Concat) e.getValue();
				// check value is set
				if(concat.getValue() == null) {
					throw new Error("ConcatControl.OnEventReceived: XML Concat message value missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				// cast source's value
				Concat concat = (Concat) e.getValue();
				// store value
				put(Data.VALUE, concat.getValue());
			}
			break;
		default:
			break;
		}
	}
}
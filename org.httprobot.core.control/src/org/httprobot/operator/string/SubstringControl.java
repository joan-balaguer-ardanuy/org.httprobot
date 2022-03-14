package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class SubstringControl
	extends AbstractStringControl<Substring> {

	/**
	 * -8786207254600689526L
	 */
	private static final long serialVersionUID = -8786207254600689526L;
		
	public SubstringControl() {
		super();
		setMessage(new Substring());
	}
	public SubstringControl(Substring message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {

				Substring message = Substring.class.cast(e.getValue());

				if (message.getStartIndex() == null || message.getEndIndex() == null) {
					throw new Error("SubstringControl.OnEventReceived: XML message is inconsistent.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				Substring message = Substring.class.cast(e.getValue());
				put(Data.START_INDEX, message.getStartIndex());
				put(Data.END_INDEX, message.getEndIndex());
			}
			break;
		default:
			break;
		}
	}
}
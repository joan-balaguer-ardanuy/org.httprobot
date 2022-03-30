package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.string.Trim;

@XmlRootElement
public final class TrimControl
	extends AbstractStringControl {

	/**
	 * 807343815903651954L
	 */
	private static final long serialVersionUID = 807343815903651954L;

	@Override
	@XmlElement
	public Trim getMessage() {
		return (Trim) super.getMessage();
	}
	
	public TrimControl() {
		super();
	}
	public TrimControl(Trim message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				Trim.class.cast(e.getValue());
			}
			break;

		default:
			break;
		}
	}
}
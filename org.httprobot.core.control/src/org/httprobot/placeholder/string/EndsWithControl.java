package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.string.EndsWith;

@XmlRootElement
public final class EndsWithControl
	extends AbstractStringControl<EndsWith> {

	/**
	 * 3494988225577418938L
	 */
	private static final long serialVersionUID = 3494988225577418938L;
	
	@Override
	@XmlElement
	public EndsWith getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(EndsWith message) {
		super.setMessage(message);
	}
	
	public EndsWithControl() {
		super();
	}
	public EndsWithControl(EndsWith message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {

			EndsWith endsWith = EndsWith.class.cast(e.getMessage());

			if (endsWith.getValue() == null) {
				throw new Error("EndsWithControl.OnControlInitialized: value XML element message is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {

			EndsWith endsWith = EndsWith.class.cast(e.getMessage());
			
			put(Data.VALUE, endsWith.getValue());
			// Send event to parent
			SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
	}
}

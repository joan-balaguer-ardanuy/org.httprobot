package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.string.Substring;

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
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {

			Substring message = Substring.class.cast(e.getMessage());

			if (message.getStartIndex() == null || message.getEndIndex() == null) {
				throw new Error("SubstringControl.OnControlInitialized: XML message is inconsistent.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {
			Substring message = Substring.class.cast(e.getMessage());
			put(Data.START_INDEX, message.getStartIndex());
			put(Data.END_INDEX, message.getEndIndex());
			// Send event to parent
			SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
	}
}
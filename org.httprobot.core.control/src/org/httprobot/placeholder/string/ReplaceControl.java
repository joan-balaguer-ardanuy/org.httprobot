package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.string.Replace;

@XmlRootElement
public final class ReplaceControl
	extends AbstractStringControl<Replace> {

	/**
	 * 7760167926576384464L
	 */
	private static final long serialVersionUID = 7760167926576384464L;

	public ReplaceControl() {
		super();
		setMessage(new Replace());
	}
	public ReplaceControl(Replace message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			Replace replace = Replace.class.cast(e.getMessage());
			if(replace.getOldString() == null) {
				throw new Error("ReplaceControl.OnControlInitialized: OldString XML element message is missing.");
			}
			if(replace.getNewString() == null) {
				throw new Error("ReplaceControl.OnControlInitialized: NewString XML element message is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {

			Replace replace = Replace.class.cast(e.getMessage());

			if (replace.getOldString() != null) {
				put(Data.OLD_STRING, replace.getOldString());
			} else {
				throw new Error("ReplaceControl.OnControlInitialized: OldString XML message element expected.");
			}
			if (replace.getNewString() != null) {
				put(Data.NEW_STRING, replace.getNewString());
			} else {
				throw new Error("ReplaceControl.OnControlInitialized: NewString XML message element expected.");
			}
			// Send event to parent
			SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
	}
}
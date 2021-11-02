package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class TrimControl
	extends AbstractStringControl<Trim> {

	/**
	 * 807343815903651954L
	 */
	private static final long serialVersionUID = 807343815903651954L;

	public TrimControl() {
		super();
		setMessage(new Trim());
	}
	public TrimControl(Trim message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {

			Trim.class.cast(e.getMessage());
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {
			// Set control ready to be iterated again.
			reset();
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.TRIM_CONTROL_LOADED));
		}
	}
}
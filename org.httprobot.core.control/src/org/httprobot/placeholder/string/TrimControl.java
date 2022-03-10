package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.string.Trim;

@XmlRootElement
public final class TrimControl
	extends AbstractStringControl<Trim> {

	/**
	 * 807343815903651954L
	 */
	private static final long serialVersionUID = 807343815903651954L;

	@Override
	@XmlElement
	public Trim getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Trim message) {
		super.setMessage(message);
	}
	
	public TrimControl() {
		super();
		setMessage(new Trim());
	}
	public TrimControl(Trim message, Control parent) {
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
			// Send event to parent
			SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
	}
}
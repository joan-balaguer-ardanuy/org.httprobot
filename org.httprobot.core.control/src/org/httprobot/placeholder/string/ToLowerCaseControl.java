package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ToLowerCaseControl
	extends AbstractStringControl<ToLowerCase> {

	/**
	 * -8140876774202456900L
	 */
	private static final long serialVersionUID = -8140876774202456900L;
	
	@Override
	@XmlElement
	public ToLowerCase getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(ToLowerCase message) {
		super.setMessage(message);
	}
	
	public ToLowerCaseControl() {
		super();
	}
	public ToLowerCaseControl(ToLowerCase message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			ToLowerCase.class.cast(e.getMessage());
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if(e.getSource().equals(this)) {
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.TO_LOWER_CASE_CONTROL_LOADED));
		}
	}
}

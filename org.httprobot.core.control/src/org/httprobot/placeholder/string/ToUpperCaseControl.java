package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ToUpperCaseControl
	extends AbstractStringControl<ToUpperCase> {

	/**
	 * -8536577675929221898L
	 */
	private static final long serialVersionUID = -8536577675929221898L;
	
	@Override
	@XmlElement
	public ToUpperCase getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(ToUpperCase message) {
		super.setMessage(message);
	}
	
	public ToUpperCaseControl() {
		super();
	}
	public ToUpperCaseControl(ToUpperCase message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			ToUpperCase.class.cast(e.getMessage());
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if(e.getSource().equals(this)) {
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.TO_UPPER_CASE_CONTROL_LOADED));
		}
	}
}
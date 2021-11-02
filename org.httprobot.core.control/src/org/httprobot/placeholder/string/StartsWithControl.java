package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class StartsWithControl 
	extends AbstractStringControl<StartsWith> {

	/**
	 * 6170464494921818052L
	 */
	private static final long serialVersionUID = 6170464494921818052L;
	
	@Override
	@XmlElement
	public StartsWith getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(StartsWith message) {
		super.setMessage(message);
	}
	
	public StartsWithControl() {
		super();
	}
	public StartsWithControl(StartsWith message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {

			StartsWith startsWith = StartsWith.class.cast(e.getMessage());

			if (startsWith.getValue() == null) {
				throw new Error("StartsWithControl.OnControlInitialized: value XML element message is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {

			StartsWith startsWith = StartsWith.class.cast(e.getMessage());
			
			put(Data.VALUE, startsWith.getValue());
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.STARTS_WITH_CONTROL_LOADED));
		}
	}
}

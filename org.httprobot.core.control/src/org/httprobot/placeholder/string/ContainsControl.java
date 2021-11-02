package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ContainsControl 
	extends AbstractOperatorControl<Contains> {

	/**
	 * -5379321827008140153L
	 */
	private static final long serialVersionUID = -5379321827008140153L;
	
	public ContainsControl() {
		super();
		setMessage(new Contains());
	}
	public ContainsControl(Contains message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {

			Contains contains = Contains.class.cast(e.getMessage());

			if (contains.getValue() == null) {
				throw new Error("ContainsControl.OnControlInitialized: value XML element message is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {

			Contains contains = Contains.class.cast(e.getMessage());

			if (contains.getValue() != null) {
				put(Data.VALUE, contains.getValue());
				// Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.CONTAINS_CONTROL_LOADED));
			} else {
				throw new Error("ContainsControl.OnControlInitialized: Value XML message element expected.");
			}
		}
	}
}
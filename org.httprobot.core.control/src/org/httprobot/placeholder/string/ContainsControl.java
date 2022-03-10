package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.string.Contains;

@XmlRootElement
public final class ContainsControl 
	extends AbstractStringControl<Contains> {

	/**
	 * -5379321827008140153L
	 */
	private static final long serialVersionUID = -5379321827008140153L;
	
	public ContainsControl() {
		super();
	}
	public ContainsControl(Contains message, Control parent) {
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
			
			put(Data.VALUE, contains.getValue());
			// Send event to parent
			SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
	}
}
package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class EqualsControl
	extends AbstractStringControl<Equals>{

	/**
	 * -9117585827351033039L
	 */
	private static final long serialVersionUID = -9117585827351033039L;

	public EqualsControl() {
		super();
		setMessage(new Equals());
	}
	public EqualsControl(Equals message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			Equals equals = Equals.class.cast(e.getMessage());
			
			if(equals.getValue() == null) {
				throw new Error("EqualsControl.OnControlInitialized: value XML element message.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {

			Equals equals = Equals.class.cast(e.getMessage());

			put(Data.VALUE, equals.getValue());
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.EQUALS_CONTROL_LOADED));
		}
	}
}
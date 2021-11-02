package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class DelimiterControl
	extends Control<Delimiter> {

	/**
	 * -4719950651306744005L
	 */
	private static final long serialVersionUID = -4719950651306744005L;

	public DelimiterControl() {
		super();
		setMessage(new Delimiter());
	}
	public DelimiterControl(Delimiter message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Delimiter delimiter = Delimiter.class.cast(e.getMessage());
			
			if(delimiter.getDelimiter() == null) {
				throw new Error("DelimiterControl.OnControlInitialized: Inconsistent Delimiter XML message.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Delimiter delimiter = Delimiter.class.cast(e.getMessage());
			
			put(Data.DELIMITER, delimiter.getDelimiter());
			CommandLineEvent(new CommandEventArgs(this, Command.DELIMITERS_CONTROL_LOADED));
		}
	}
}
package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class TryParseControl
	extends AbstractOperatorControl<TryParse> {

	/**
	 * -5977456131566401187L
	 */
	private static final long serialVersionUID = -5977456131566401187L;
	
	public TryParseControl() {
		super();
		setMessage(new TryParse());
	}
	public TryParseControl(TryParse message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			TryParse tryParse = TryParse.class.cast(e.getMessage());
			if(tryParse.getFieldType() == null) {
				throw new Error("TryParseControl.OnControlInitialized: FieldType XML element message is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if(e.getSource().equals(this)) {
			TryParse tryParse = TryParse.class.cast(e.getMessage());
			if(tryParse.getFieldType() != null) {
				put(Data.FIELD_TYPE, tryParse.getFieldType());
				// Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.TRY_PARSE_CONTROL_LOADED));
			}
		}
	}
}
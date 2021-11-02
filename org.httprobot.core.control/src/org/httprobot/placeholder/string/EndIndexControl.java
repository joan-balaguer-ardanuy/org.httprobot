package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class EndIndexControl
	extends Control<EndIndex> {

	/**
	 * -517205521315786271L
	 */
	private static final long serialVersionUID = -517205521315786271L;

	public EndIndexControl() {
		super();
		setMessage(new EndIndex());
	}
	public EndIndexControl(EndIndex message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			EndIndex endIndex = EndIndex.class.cast(e.getMessage());
			
			if(endIndex.getStringValue() == null && endIndex.getIntegerValue() == null) {
				throw new Error("EndIndexControl.OnControlInitialized: Inconsistent StartIndex XML message.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			EndIndex endIndex = EndIndex.class.cast(e.getMessage());
			
			if(endIndex.getStringValue() != null) {
				put(Data.STRING_VALUE, endIndex.getStringValue());
			}
			else if(endIndex.getIntegerValue() != null) {
				put(Data.INTEGER_VALUE, endIndex.getIntegerValue());
			}
			else if(endIndex.getOffset() != null) {
				put(Data.OFFSET, endIndex.getOffset());
			}
			else {
				throw new Error("EndIndexControl.OnControlLoaded: Inconsistent EndIndex XML message.");
			}
			CommandLineEvent(new CommandEventArgs(this, Command.END_INDEX_CONTROL_LOADED));
		}
	}
}
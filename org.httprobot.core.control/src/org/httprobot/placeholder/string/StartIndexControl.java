package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class StartIndexControl 
	extends Control<StartIndex> {

	/**
	 * 8066989877815359482L
	 */
	private static final long serialVersionUID = 8066989877815359482L;
	
	public StartIndexControl() {
		super();
		setMessage(new StartIndex());
	}
	public StartIndexControl(StartIndex message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			StartIndex startIndex = StartIndex.class.cast(e.getMessage());
			
			if(startIndex.getStringValue() == null && startIndex.getIntegerValue() == null) {
				throw new Error("StartIndexControl.OnControlInitialized: Inconsistent StartIndex XML message.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			StartIndex startIndex = StartIndex.class.cast(e.getMessage());
			
			if(startIndex.getStringValue() != null) {
				put(Data.STRING_VALUE, startIndex.getStringValue());
			}
			else if(startIndex.getIntegerValue() != null) {
				put(Data.INTEGER_VALUE, startIndex.getIntegerValue());
			}
			else if(startIndex.getOffset() != null) {
				put(Data.OFFSET, startIndex.getOffset());
			}
			else {
				throw new Error("StartIndexControl.OnControlLoaded: Inconsistent StartIndex XML message.");
			}
			CommandLineEvent(new CommandEventArgs(this, Command.END_INDEX_CONTROL_LOADED));
		}
	}
}

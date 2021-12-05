package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class StartUrlControl
	extends Control<StartUrl> {

	/**
	 * 2360092739620463628L
	 */
	private static final long serialVersionUID = 2360092739620463628L;
	
	public StartUrlControl() {
		super();
	}
	public StartUrlControl(StartUrl message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			StartUrl startUrl = StartUrl.class.cast(e.getMessage());
			
			if(startUrl.getKey() == null || startUrl.getValue() == null) {
				throw new Error("StartUrlControl.OnControlInitialized: Inconsistent StartUrl XML message.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			StartUrl startUrl = StartUrl.class.cast(e.getMessage());
			put(Data.KEY, startUrl.getKey());
			put(Data.VALUE, startUrl.getValue());
			CommandLineEvent(new CommandEventArgs(this, Command.START_URL_CONTROL_LOADED));
		}
	}
}
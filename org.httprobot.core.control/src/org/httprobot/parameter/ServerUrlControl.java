package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ServerUrlControl 
	extends Control<ServerUrl> {

	/**
	 * 4345480336905549105L
	 */
	private static final long serialVersionUID = 4345480336905549105L;
	
	public ServerUrlControl() {
		super();		
	}
	public ServerUrlControl(ServerUrl message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			ServerUrl serverUrl = ServerUrl.class.cast(e.getMessage());
			
			if(serverUrl.getKey() == null || serverUrl.getValue() == null) {
				throw new Error("ServerUrlControl.OnControlInitialized: Inconsistent ServerUrl XML message.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			ServerUrl serverUrl = ServerUrl.class.cast(e.getMessage());
			put(Data.KEY, serverUrl.getKey());
			put(Data.VALUE, serverUrl.getValue());
			CommandListenerEvent(new CommandEventArgs(this, Command.SERVER_URL_CONTROL_LOADED));
		}
	}
}

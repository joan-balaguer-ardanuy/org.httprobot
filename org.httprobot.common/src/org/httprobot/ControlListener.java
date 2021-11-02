package org.httprobot;

import org.httprobot.event.ControlEventArgs;

public interface ControlListener extends MessageListener {
	
	public void OnControlInitialized(ControlEventArgs e);
	
	public void OnControlLoaded(ControlEventArgs e);
}

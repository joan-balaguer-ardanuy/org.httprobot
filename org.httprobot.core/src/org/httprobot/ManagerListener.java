package org.httprobot;

import org.httprobot.event.ManagerEventArgs;

public interface ManagerListener extends MessageListener {
	
	void start();
	void stop();
	
	void OnManagerEvent(ManagerEventArgs e);
}
package org.httprobot;

import java.io.Serializable;
import java.util.EventListener;

import org.httprobot.event.CommandEventArgs;

public interface CommandListener extends EventListener, Serializable {

	String getPath();
	void setPath(String path);
	
	void OnCommandReceived(CommandEventArgs e);
}

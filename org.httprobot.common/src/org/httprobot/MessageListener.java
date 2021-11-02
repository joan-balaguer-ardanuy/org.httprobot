package org.httprobot;

import java.util.UUID;

import org.httprobot.event.MessageEventArgs;

public interface MessageListener extends CommandListener {

	UUID getUuid();
	void setUuid(UUID uuid);
	
	void OnMessageMarshalled(MessageEventArgs e);
	void OnMessageUnmarshalled(MessageEventArgs e);
}

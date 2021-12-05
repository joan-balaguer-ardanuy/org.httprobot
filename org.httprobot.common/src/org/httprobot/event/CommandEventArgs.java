package org.httprobot.event;

import org.httprobot.Command;
import org.httprobot.EventType;
import org.httprobot.MessageListener;

public class CommandEventArgs extends EventArgs {

	/**
	 * -533268545525043111L
	 */
	private static final long serialVersionUID = -533268545525043111L;

	Command command;
	
	public Command getCommand() {
		return command;
	}

	public CommandEventArgs(MessageListener source, Command command) {
		super(source, EventType.CLI);
		this.command = command;
	}

}

package org.httprobot.event;

import org.httprobot.Enums.EventType;

public class DataEventArgs extends EventArgs {

	/**
	 * 8279778428043980196L
	 */
	private static final long serialVersionUID = 8279778428043980196L;

	public DataEventArgs(Object source) {
		super(source, EventType.DATA);
	}
}
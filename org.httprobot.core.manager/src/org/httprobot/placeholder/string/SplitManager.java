package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class SplitManager
	extends AbstractStringManager<SplitControl> {

	/**
	 * -2674949404675117218L
	 */
	private static final long serialVersionUID = -2674949404675117218L;
	
	public SplitManager() {
		super();
	}
	public SplitManager(Split message, Manager<?> parent) {
		super(message, SplitControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
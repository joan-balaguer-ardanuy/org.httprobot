package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class SubstringManager
	extends AbstractStringManager<SubstringControl> {

	/**
	 * 7868954939148015302L
	 */
	private static final long serialVersionUID = 7868954939148015302L;

	public SubstringManager() {
		super();
	}
	public SubstringManager(Substring message, Manager<?> parent) {
		super(message, SubstringControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
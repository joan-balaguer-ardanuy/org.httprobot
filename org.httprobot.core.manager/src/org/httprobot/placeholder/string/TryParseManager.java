package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class TryParseManager 
	extends AbstractStringManager<TryParseControl> {

	/**
	 * 3702738941817036919L
	 */
	private static final long serialVersionUID = 3702738941817036919L;

	public TryParseManager() {
		super();
	}
	public TryParseManager(TryParse message, Manager<?> parent) {
		super(message, TryParseControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class TrimManager 
	extends AbstractStringManager<TrimControl> {

	/**
	 * 8160161481205354596L
	 */
	private static final long serialVersionUID = 8160161481205354596L;
	
	public TrimManager() {
		super();
	}
	public TrimManager(Trim message, Manager<?> parent) {
		super(message, TrimControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
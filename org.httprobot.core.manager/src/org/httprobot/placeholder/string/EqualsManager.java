package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class EqualsManager 
	extends AbstractStringManager<EqualsControl> {

	/**
	 * -481593600327061747L
	 */
	private static final long serialVersionUID = -481593600327061747L;

	public EqualsManager() {
		super();
	}
	public EqualsManager(Equals message, Manager<?> parent) {
		super(message, EqualsControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
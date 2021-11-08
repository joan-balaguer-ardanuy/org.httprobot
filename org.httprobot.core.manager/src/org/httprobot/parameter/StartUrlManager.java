package org.httprobot.parameter;

import org.httprobot.AbstractManager;
import org.httprobot.ManagerListener;
import org.httprobot.event.ManagerEventArgs;

public class StartUrlManager
	extends AbstractManager<StartUrlControl>
		implements java.util.Map.Entry<String, String> {

	/**
	 * -3606336418056710722L
	 */
	private static final long serialVersionUID = -3606336418056710722L;

	@Override
	public String getKey() {
		return getControl().getMessage().getKey();
	}

	@Override
	public String getValue() {
		return getControl().getMessage().getValue();
	}

	@Override
	public String setValue(String value) {
		return null;
	}
	
	public StartUrlManager() {
		super();
	}
	public StartUrlManager(StartUrl message, ManagerListener parent) {
		super(message, StartUrlControl.class, parent);
	}
	
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}

}

package org.httprobot.parameter;

import org.httprobot.Manager;
import org.httprobot.ManagerListener;
import org.httprobot.event.ManagerEventArgs;

public class ServerUrlManager 
	extends Manager<ServerUrlControl>
		implements java.util.Map.Entry<String, String> {

	/**
	 * 4423575643357009527L
	 */
	private static final long serialVersionUID = 4423575643357009527L;

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
	
	public ServerUrlManager() {
		super();
	}
	public ServerUrlManager(ServerUrl message, ManagerListener parent) {
		super(message, ServerUrlControl.class, parent);
	}
	
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
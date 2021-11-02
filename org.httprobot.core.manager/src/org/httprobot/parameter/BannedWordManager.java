package org.httprobot.parameter;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class BannedWordManager
	extends Manager<BannedWordControl> 
		implements java.util.Map.Entry<String,String> {

	/**
	 * -5451324194154557676L
	 */
	private static final long serialVersionUID = -5451324194154557676L;
	
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
	
	public BannedWordManager() {
	}
	public BannedWordManager(BannedWord message, Manager<?> parent) {
		super(message, BannedWordControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
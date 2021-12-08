package org.httprobot.content;

import org.httprobot.EntryManager;
import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

public class FieldRefManager 
	extends EntryManager<FieldRef, InputField, FieldRefControl> {

	/**
	 * 1140349784992668189L
	 */
	private static final long serialVersionUID = 1140349784992668189L;
	
	@Override
	public FieldRef getKey() {
		return getControl().getMessage();
	}
	
	public FieldRefManager() {
		super();
	}
	public FieldRefManager(FieldRef message, ManagerListener parent) {
		super(message, FieldRefControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
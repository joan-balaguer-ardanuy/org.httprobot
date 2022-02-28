package org.httprobot.content;

import org.httprobot.MappingManager;
import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

public class FieldRefManager 
	extends MappingManager<FieldRef, InputField, FieldRefControl> {

	/**
	 * 1140349784992668189L
	 */
	private static final long serialVersionUID = 1140349784992668189L;
	
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
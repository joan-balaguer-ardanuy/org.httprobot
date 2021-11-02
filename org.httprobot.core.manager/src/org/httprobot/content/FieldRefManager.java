package org.httprobot.content;

import org.httprobot.Manager;
import org.httprobot.Enums.ManagerEventType;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

public class FieldRefManager 
	extends Manager<FieldRefControl> 
		implements java.util.Map.Entry<FieldRef, InputField>{

	/**
	 * 1140349784992668189L
	 */
	private static final long serialVersionUID = 1140349784992668189L;

	InputField value;
	
	@Override
	public FieldRef getKey() {
		return getControl().getMessage();
	}
	@Override
	public InputField getValue() {
		return value;
	}
	@Override
	public InputField setValue(InputField value) {
		InputField oldValue = this.value;
		this.value = value;
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		return oldValue;
	}
	
	public FieldRefManager() {
		super();
	}
	public FieldRefManager(FieldRef message, Manager<?> parent) {
		super(message, FieldRefControl.class, parent);
	}
	
	@Override
	public void OnCommandReceived(CommandEventArgs e) {

	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
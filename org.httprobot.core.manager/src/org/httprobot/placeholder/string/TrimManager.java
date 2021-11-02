package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

public class TrimManager 
	extends AbstractStringManager<TrimControl> {

	/**
	 * 8160161481205354596L
	 */
	private static final long serialVersionUID = 8160161481205354596L;
	
	@Override
	public InputField setValue(InputField value) {
		value.setValue(value.getValue().toString().trim());
		return super.setValue(value);
	}
	
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
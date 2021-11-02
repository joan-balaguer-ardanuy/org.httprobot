package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

public class ConcatManager
	extends AbstractStringManager<ConcatControl> {

	/**
	 * -2674949404675117218L
	 */
	private static final long serialVersionUID = -2674949404675117218L;
	
	@Override
	public InputField setValue(InputField value) {
		value.setValue(getKey().concat(getControl().getMessage().getValue()));
		return super.setValue(value);
	}
	
	public ConcatManager() {
		super();
	}
	public ConcatManager(Concat message, Manager<?> parent) {
		super(message, ConcatControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

public class ReplaceManager
	extends AbstractStringManager<ReplaceControl> {

	/**
	 * -4082786732248275675L
	 */
	private static final long serialVersionUID = -4082786732248275675L;

	@Override
	public InputField setValue(InputField value) {
		return super.setValue(value);
	}
	
	public ReplaceManager() {
		super();
	}
	public ReplaceManager(Replace message, Manager<?> parent) {
		super(message, ReplaceControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
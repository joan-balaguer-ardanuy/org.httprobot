package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

public class ContainsManager
	extends AbstractStringManager<ContainsControl> {

	/**
	 * 6954038560443161414L
	 */
	private static final long serialVersionUID = 6954038560443161414L;

	@Override
	public InputField setValue(InputField value) {
		if(!getKey().contains(getControl().getMessage().getValue())) {
			value.setValue(null);
		}
		return super.setValue(value);
	}
	
	public ContainsManager() {
		super();
	}
	public ContainsManager(Contains message, Manager<?> parent) {
		super(message, ContainsControl.class, parent);
	}
	
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		super.OnManagerEvent(e);
	}
}
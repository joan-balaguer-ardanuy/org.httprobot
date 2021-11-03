package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;

public class ToLowerCaseManager 
	extends AbstractStringManager<ToLowerCaseControl> {

	/**
	 * -912172374068787372L
	 */
	private static final long serialVersionUID = -912172374068787372L;
	
	@Override
	public InputField setValue(InputField value) {
		value.setValue(getKey().toLowerCase());
		return super.setValue(value);
	}
	
	public ToLowerCaseManager() {
		super();
	}
	public ToLowerCaseManager(ToLowerCase message, Manager<?> parent) {
		super(message, ToLowerCaseControl.class, parent);
	}
}
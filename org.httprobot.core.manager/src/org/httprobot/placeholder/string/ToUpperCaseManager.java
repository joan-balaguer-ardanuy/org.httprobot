package org.httprobot.placeholder.string;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;

public class ToUpperCaseManager
	extends AbstractStringManager<ToUpperCaseControl> {

	/**
	 * -7874627263860439318L
	 */
	private static final long serialVersionUID = -7874627263860439318L;
	
	@Override
	public InputField setValue(InputField value) {
		value.setValue(getKey().toUpperCase());
		return super.setValue(value);
	}

	public ToUpperCaseManager() {
		super();
	}
	public ToUpperCaseManager(ToUpperCase message, Manager<?> parent) {
		super(message, ToUpperCaseControl.class, parent);
	}
}

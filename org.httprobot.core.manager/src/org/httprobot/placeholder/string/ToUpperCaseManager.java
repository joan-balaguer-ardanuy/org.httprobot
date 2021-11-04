package org.httprobot.placeholder.string;

import org.httprobot.Manager;

public class ToUpperCaseManager
	extends AbstractStringManager<ToUpperCaseControl> {

	/**
	 * -7874627263860439318L
	 */
	private static final long serialVersionUID = -7874627263860439318L;

	public ToUpperCaseManager() {
		super();
	}
	public ToUpperCaseManager(ToUpperCase message, Manager<?> parent) {
		super(message, ToUpperCaseControl.class, parent);
	}
}

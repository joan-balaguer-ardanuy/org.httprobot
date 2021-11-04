package org.httprobot.placeholder.string;

import org.httprobot.Manager;

public class ToLowerCaseManager 
	extends AbstractStringManager<ToLowerCaseControl> {

	/**
	 * -912172374068787372L
	 */
	private static final long serialVersionUID = -912172374068787372L;
	
	public ToLowerCaseManager() {
		super();
	}
	public ToLowerCaseManager(ToLowerCase message, Manager<?> parent) {
		super(message, ToLowerCaseControl.class, parent);
	}
}
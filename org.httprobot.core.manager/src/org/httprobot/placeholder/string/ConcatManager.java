package org.httprobot.placeholder.string;

import org.httprobot.Manager;

public class ConcatManager
	extends AbstractStringManager<ConcatControl> {

	/**
	 * -2674949404675117218L
	 */
	private static final long serialVersionUID = -2674949404675117218L;
	
	public ConcatManager() {
		super();
	}
	public ConcatManager(Concat message, Manager<?> parent) {
		super(message, ConcatControl.class, parent);
	}
}
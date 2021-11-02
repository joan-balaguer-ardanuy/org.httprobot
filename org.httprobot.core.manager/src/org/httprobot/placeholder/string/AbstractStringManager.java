package org.httprobot.placeholder.string;

import org.httprobot.Control;
import org.httprobot.Manager;
import org.httprobot.AbstractString;
import org.httprobot.placeholder.AbstractPlaceholderManager;

public abstract class AbstractStringManager<T extends Control<?>>
	extends AbstractPlaceholderManager<String,T> {

	/**
	 * 9016980694923790707L
	 */
	private static final long serialVersionUID = 9016980694923790707L;
	
	public AbstractStringManager() {
		super();
	}
	public AbstractStringManager(AbstractString message, Class<T> type, Manager<?> parent) {
		super(message, type, parent);
	}
}

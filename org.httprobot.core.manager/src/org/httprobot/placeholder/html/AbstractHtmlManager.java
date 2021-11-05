package org.httprobot.placeholder.html;

import org.httprobot.Control;
import org.httprobot.AbstractHtml;
import org.httprobot.Manager;
import org.httprobot.placeholder.AbstractPlaceholderManager;

public abstract class AbstractHtmlManager<K,V,T extends Control<?>>
	extends AbstractPlaceholderManager<K,V,T> {

	/**
	 * 957350695292804803L
	 */
	private static final long serialVersionUID = 957350695292804803L;

	public AbstractHtmlManager() {
		super();
	}
	public AbstractHtmlManager(AbstractHtml message, Class<T> type, Manager<?> parent) {
		super(message, type, parent);
	}
}
package org.httprobot.placeholder.html;

import org.httprobot.Control;
import org.httprobot.AbstractHtml;
import org.httprobot.ManagerListener;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.placeholder.AbstractPlaceholderManager;
import org.httprobot.unit.ContainsElementManager;
import org.httprobot.unit.ElementManager;

public abstract class AbstractHtmlManager<K,V,T extends Control<?>>
	extends AbstractPlaceholderManager<K,V,T> {

	/**
	 * 957350695292804803L
	 */
	private static final long serialVersionUID = 957350695292804803L;


	public AbstractHtmlManager() {
		super();
	}
	public AbstractHtmlManager(AbstractHtml message, Class<T> type, ManagerListener parent) {
		super(message, type, parent);
	}
	
}
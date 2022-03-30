package org.httprobot.placeholder.html;

import org.httprobot.Control;
import org.httprobot.Parent;
import org.httprobot.placeholder.AbstractOperatorParent;
import org.httprobot.Message;

public abstract class AbstractHtmlParent<K,V>
	extends AbstractOperatorParent<K,V> {

	/**
	 * 957350695292804803L
	 */
	private static final long serialVersionUID = 957350695292804803L;


	public AbstractHtmlParent() {
		super();
	}
	public AbstractHtmlParent(Message message, Class<? extends Control> type, Parent parent) {
		super(message, type, parent);
	}
	
}
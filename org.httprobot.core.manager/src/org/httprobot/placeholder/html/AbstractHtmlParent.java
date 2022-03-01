package org.httprobot.placeholder.html;

import org.httprobot.Control;
import org.httprobot.AbstractHtml;
import org.httprobot.ParentListener;
import org.httprobot.placeholder.AbstractPlaceholderParent;

public abstract class AbstractHtmlParent<K,V,T extends Control<?>>
	extends AbstractPlaceholderParent<K,V,T> {

	/**
	 * 957350695292804803L
	 */
	private static final long serialVersionUID = 957350695292804803L;


	public AbstractHtmlParent() {
		super();
	}
	public AbstractHtmlParent(AbstractHtml message, Class<T> type, ParentListener parent) {
		super(message, type, parent);
	}
	
}
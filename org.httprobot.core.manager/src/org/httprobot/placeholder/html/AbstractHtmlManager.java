package org.httprobot.placeholder.html;

import java.util.Set;

import org.httprobot.Control;
import org.httprobot.AbstractHtml;
import org.httprobot.Manager;
import org.httprobot.placeholder.AbstractPlaceholderManager;

import com.gargoylesoftware.htmlunit.html.DomNode;

public abstract class AbstractHtmlManager<T extends Control<?>>
	extends AbstractPlaceholderManager<Set<DomNode>,T> {

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
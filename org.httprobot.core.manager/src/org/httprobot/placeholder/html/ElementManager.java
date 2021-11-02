package org.httprobot.placeholder.html;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class ElementManager
	extends AbstractHtmlManager<ElementControl> {

	/**
	 * 8813593464366243836L
	 */
	private static final long serialVersionUID = 8813593464366243836L;
	
	public ElementManager() {
		super();
	}
	public ElementManager(Element message, Manager<?> parent) {
		super(message, ElementControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
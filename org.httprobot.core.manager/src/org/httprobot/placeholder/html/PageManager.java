package org.httprobot.placeholder.html;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class PageManager
	extends AbstractHtmlManager<PageControl> {
	
	/**
	 * 4976479148819311844L
	 */
	private static final long serialVersionUID = 4976479148819311844L;
	
	public PageManager() {
		super();
	}
	public PageManager(Page message, Manager<?> parent) {
		super(message, PageControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
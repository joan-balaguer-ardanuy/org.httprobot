package org.httprobot.placeholder.html;

import java.util.Set;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class ElementManager
	extends AbstractHtmlManager<InputField, Set<DomNode>, ElementControl> {

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
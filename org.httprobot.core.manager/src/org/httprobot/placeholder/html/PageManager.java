package org.httprobot.placeholder.html;

import java.util.Map;
import java.util.Set;

import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class PageManager
	extends AbstractHtmlManager<Map<InputField,Set<DomNode>>,Map<InputField,Set<DomNode>>, PageControl> {
	
	/**
	 * 4976479148819311844L
	 */
	private static final long serialVersionUID = 4976479148819311844L;
	
	public PageManager() {
		super();
	}
	public PageManager(Page message, ManagerListener parent) {
		super(message, PageControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
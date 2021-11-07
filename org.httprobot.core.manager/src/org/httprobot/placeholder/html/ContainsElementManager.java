package org.httprobot.placeholder.html;

import java.util.List;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class ContainsElementManager 
	extends AbstractHtmlManager<DomNode, Boolean, ContainsElementControl>{

	/**
	 * -756242644019692484L
	 */
	private static final long serialVersionUID = -756242644019692484L;

	public ContainsElementManager() {
		super();
	}
	public ContainsElementManager(ContainsElement message, Manager<?> parent) {
		super(message, ContainsElementControl.class, parent);
	}

	@Override
	public Boolean put(DomNode key, Boolean value) {
		List<Object> result = key.getByXPath(getControl().getMessage().getXPath());
		if(!result.isEmpty()) {
			value = true;
		} else {
			value = false;
		}
		return super.put(key, value);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
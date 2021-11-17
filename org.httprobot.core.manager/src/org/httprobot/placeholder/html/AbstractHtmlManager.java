package org.httprobot.placeholder.html;

import org.httprobot.Control;
import org.httprobot.AbstractHtml;
import org.httprobot.ManagerListener;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.placeholder.AbstractPlaceholderManager;

public abstract class AbstractHtmlManager<K,V,T extends Control<?>>
	extends AbstractPlaceholderManager<K,V,T> {

	/**
	 * 957350695292804803L
	 */
	private static final long serialVersionUID = 957350695292804803L;

	ElementManager elementManager;
	ContainsElementManager containsElementManager;
	
	public AbstractHtmlManager() {
		super();
	}
	public AbstractHtmlManager(AbstractHtml message, Class<T> type, ManagerListener parent) {
		super(message, type, parent);
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTAINS_ELEMENT_CONTROL_LOADED:
			if(e.getSource() instanceof ContainsElementControl) {
				ContainsElement containsElement = ContainsElementControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.CONTAINS_ELEMENT).equals(containsElement)) {
					containsElementManager = new ContainsElementManager(containsElement, this);
					addChildManager(containsElementManager);
				}
			}
			break;
		case ELEMENT_CONTROL_LOADED:
			if(e.getSource() instanceof ElementControl) {
				Element containsElement = ElementControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.ELEMENT).equals(containsElement)) {
					elementManager = new ElementManager(containsElement, this);
					addChildManager(elementManager);
				}
			}
			break;
		default:
			break;
		}
	}
}
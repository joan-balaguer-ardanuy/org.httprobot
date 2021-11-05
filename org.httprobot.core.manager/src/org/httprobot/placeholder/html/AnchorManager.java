package org.httprobot.placeholder.html;

import java.util.Set;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class AnchorManager
	extends AbstractHtmlManager<InputField, Set<DomNode> ,AnchorControl>{

	/**
	 * 2578347858674092447L
	 */
	private static final long serialVersionUID = 2578347858674092447L;
	
	public AnchorManager() {
		super();
	}
	public AnchorManager(Anchor message, Manager<?> parent) {
		super(message, AnchorControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
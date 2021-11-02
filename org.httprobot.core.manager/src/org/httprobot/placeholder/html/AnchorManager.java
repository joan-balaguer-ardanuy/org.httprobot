package org.httprobot.placeholder.html;

import org.httprobot.Manager;
import org.httprobot.event.ManagerEventArgs;

public class AnchorManager
	extends AbstractHtmlManager<AnchorControl>{

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
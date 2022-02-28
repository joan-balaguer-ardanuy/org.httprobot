package org.httprobot.placeholder.html;

import java.util.Set;

import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebElement;

public class AnchorManager
	extends AbstractHtmlManager<InputField, Set<WebElement>, AnchorControl>{

	/**
	 * 2578347858674092447L
	 */
	private static final long serialVersionUID = 2578347858674092447L;
	
	public AnchorManager() {
		super();
	}
	public AnchorManager(Anchor message, ManagerListener parent) {
		super(message, AnchorControl.class, parent);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
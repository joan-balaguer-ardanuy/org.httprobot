package org.httprobot.placeholder.html;

import java.util.Set;

import org.httprobot.ParentListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebElement;

public class AnchorParent
	extends AbstractHtmlParent<InputField, Set<WebElement>, AnchorControl>{

	/**
	 * 2578347858674092447L
	 */
	private static final long serialVersionUID = 2578347858674092447L;
	
	public AnchorParent() {
		super();
	}
	public AnchorParent(Anchor message, ParentListener parent) {
		super(message, AnchorControl.class, parent);
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}
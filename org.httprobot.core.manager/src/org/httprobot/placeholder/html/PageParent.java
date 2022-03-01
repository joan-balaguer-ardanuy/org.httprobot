package org.httprobot.placeholder.html;

import java.util.Map;
import java.util.Set;

import org.httprobot.ParentListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebElement;

public class PageParent
	extends AbstractHtmlParent<Map<InputField,Set<WebElement>>,Map<InputField,Set<WebElement>>, PageControl> {
	
	/**
	 * 4976479148819311844L
	 */
	private static final long serialVersionUID = 4976479148819311844L;
	
	public PageParent() {
		super();
	}
	public PageParent(Page message, ParentListener parent) {
		super(message, PageControl.class, parent);
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}
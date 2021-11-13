package org.httprobot.placeholder.html;

import java.util.List;

import org.httprobot.ManagerListener;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContainsElementManager 
	extends AbstractHtmlManager<WebElement, Boolean, ContainsElementControl>{

	/**
	 * -756242644019692484L
	 */
	private static final long serialVersionUID = -756242644019692484L;

	public ContainsElementManager() {
		super();
	}
	public ContainsElementManager(ContainsElement message, ManagerListener parent) {
		super(message, ContainsElementControl.class, parent);
	}

	@Override
	public Boolean put(WebElement key, Boolean value) {
		List<WebElement> result = key.findElements(By.xpath(getControl().getMessage().getXPath()));
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
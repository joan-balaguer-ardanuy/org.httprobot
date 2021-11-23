package org.httprobot.unit;

import org.httprobot.Enums.Data;
import org.httprobot.Manager;
import org.httprobot.ManagerListener;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PaginatorManager 
	extends Manager<WebElement, WebElement, PaginatorControl> {

	/**
	 * 6281489078874071651L
	 */
	private static final long serialVersionUID = 6281489078874071651L;

	public PaginatorManager() {
	
	}
	public PaginatorManager(Paginator message, ManagerListener parent) {
		super(message, PaginatorControl.class, parent);
	}

	@Override
	public WebElement put(WebElement key, WebElement value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		
		for(WebElement element : key.findElements(By.xpath((String) getControl().get(Data.XPATH)))) {
			if (element.isDisplayed() || element.isEnabled()) {
				if (element.getText().contains((String) getControl().get(Data.PAGINATOR_ANCHOR_TEXT))) {
					value = element;
					setValue(value);
					return super.put(key, value);
				}
			} else {
				break;
			}
		}
		return null;
	}
	
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
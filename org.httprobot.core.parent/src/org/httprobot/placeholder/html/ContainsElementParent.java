package org.httprobot.placeholder.html;

import java.util.List;

import org.httprobot.Parent;
import org.httprobot.placeholder.html.ContainsElement;
import org.httprobot.placeholder.html.ContainsElementControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContainsElementParent 
	extends AbstractHtmlParent<WebElement, Boolean>{

	/**
	 * -756242644019692484L
	 */
	private static final long serialVersionUID = -756242644019692484L;

	@Override
	public ContainsElementControl getControl() {
		return (ContainsElementControl) super.getControl();
	}
	
	public ContainsElementParent() {
		super();
	}
	public ContainsElementParent(ContainsElement message, Parent parent) {
		super(message, ContainsElementControl.class, parent);
	}

	@Override
	public Boolean put(WebElement key, Boolean value) {
		keySet().add(key);
		setKey(key);
		
		List<WebElement> result = key.findElements(By.xpath(getControl().getMessage().getXPath()));
		if(!result.isEmpty()) {
			value = true;
		} else {
			value = false;
		}
		setValue(value);
		return super.put(key, value);
	}
}
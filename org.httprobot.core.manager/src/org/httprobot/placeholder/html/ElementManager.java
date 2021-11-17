package org.httprobot.placeholder.html;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Enums.Data;
import org.httprobot.Enums.ManagerEventType;
import org.httprobot.ManagerListener;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@XmlRootElement
public final class ElementManager
	extends AbstractHtmlManager<WebElement, Set<WebElement>, ElementControl> {

	/**
	 * 8813593464366243836L
	 */
	private static final long serialVersionUID = 8813593464366243836L;
	
	@Override
	@XmlElement
	public ElementControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ElementControl control) {
		super.setControl(control);
	}
	
	public ElementManager() {
		super();
	}
	public ElementManager(Element message, ManagerListener parent) {
		super(message, ElementControl.class, parent);
	}
	@Override
	public Set<WebElement> put(WebElement key, Set<WebElement> value) {
		keySet().add(key);
		List<WebElement> result = key.findElements(By.xpath(getControl().getMessage().getXPath()));
		for (WebElement webElement : result) {
			value.add(webElement);
			ManagerEvent(new ManagerEventArgs(this, webElement, ManagerEventType.NEW_ELEMENT));
			if (containsElementManager != null) {
				containsElementManager.put(webElement, null);
				if (containsElementManager.getValue()) {
					if (elementManager != null) {
						elementManager.put(webElement, new LinkedHashSet<WebElement>());
					}
				}
			} else if (elementManager != null) {
				elementManager.put(webElement, new LinkedHashSet<WebElement>());
			}
		}
		return super.put(key, value);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {

 		switch (e.getManagerEventType()) {
		case STARTED:
			
			break;
		case FINISHED:
			if(e.getSource().equals(elementManager)) {
				ManagerEvent(new ManagerEventArgs(this, elementManager.getValue(), ManagerEventType.ELEMENT_SET_COMPLETED));
			}
			break;
		default:
			break;
		}
	}
}
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
import org.openqa.selenium.WebElement;

@XmlRootElement
public final class ElementManager
	extends AbstractHtmlManager<WebElement, Set<WebElement>, ElementControl> {

	/**
	 * 8813593464366243836L
	 */
	private static final long serialVersionUID = 8813593464366243836L;
	
	ElementManager elementManager;
	ContainsElementManager containsElementManager;
	
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
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {

 		switch (e.getManagerEventType()) {
		case STARTED:
			
			break;
		case FINISHED:
			if(e.getSource().equals(elementManager)) {
				ManagerEvent(new ManagerEventArgs(this, elementManager.getValue(), ManagerEventType.DOM_SET_COMPLETED));
			}
			break;
		default:
			break;
		}
	}
}
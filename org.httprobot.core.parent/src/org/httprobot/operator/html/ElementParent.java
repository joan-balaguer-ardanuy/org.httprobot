package org.httprobot.operator.html;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Data;
import org.httprobot.EventType;
import org.httprobot.Parent;
import org.httprobot.event.EventArgs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@XmlRootElement
public final class ElementParent
	extends AbstractHtmlParent<WebElement, Set<WebElement>> {

	/**
	 * 8813593464366243836L
	 */
	private static final long serialVersionUID = 8813593464366243836L;
	
	ElementParent elementManager;
	ContainsElementParent containsElementManager;
	
	@Override
	@XmlElement
	public ElementControl getControl() {
		return (ElementControl) super.getControl();
	}
	
	public ElementParent() {
		super();
	}
	public ElementParent(Element message, Parent parent) {
		super(message, ElementControl.class, parent);
	}
	@Override
	public Set<WebElement> put(WebElement key, Set<WebElement> value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		List<WebElement> result = key.findElements(By.xpath((String) getControl().get(Data.XPATH)));
		for (WebElement element : result) {
			value.add(element);
			SendEvent(new EventArgs(this, element, EventType.NEW_ELEMENT));
			if (containsElementManager != null) {
				containsElementManager.put(key, null);
				if (containsElementManager.getValue()) {
					if (elementManager != null) {
						elementManager.put(key, new LinkedHashSet<WebElement>());
					}
				}
			} else if (elementManager != null) {
				elementManager.put(key, new LinkedHashSet<WebElement>());
			}
		}
		return super.put(key, value);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		
		switch (e.getEventType()) {
		case NEW_ELEMENT:
			if(!e.getSource().equals(this)) {
//				SendEvent(e);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource() instanceof ContainsElementControl) {
				ContainsElement containsElement = ContainsElementControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.CONTAINS_ELEMENT).equals(containsElement)) {
					containsElementManager = new ContainsElementParent(containsElement, this);
					addChild(containsElementManager);
				}
			} else if(e.getSource() instanceof ElementControl) {
				Element containsElement = ElementControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.ELEMENT).equals(containsElement)) {
					elementManager = new ElementParent(containsElement, this);
					addChild(elementManager);
				}
			}
			break;
		default:
			break;
		}
	}
}
package org.httprobot.placeholder.html;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Data;
import org.httprobot.ManagerEventType;
import org.httprobot.Listener;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.operator.html.ContainsElement;
import org.httprobot.operator.html.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@XmlRootElement
public final class ElementParent
	extends AbstractHtmlParent<WebElement, Set<WebElement>, ElementControl> {

	/**
	 * 8813593464366243836L
	 */
	private static final long serialVersionUID = 8813593464366243836L;
	
	ElementParent elementManager;
	ContainsElementParent containsElementManager;
	
	@Override
	@XmlElement
	public ElementControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ElementControl control) {
		super.setControl(control);
	}
	
	public ElementParent() {
		super();
	}
	public ElementParent(Element message, Listener parent) {
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
			ManagerEvent(new ManagerEventArgs(this, element, ManagerEventType.NEW_ELEMENT));
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
	public void OnParentEvent(ManagerEventArgs e) {

 		switch (e.getManagerEventType()) {
		case STARTED:
			
			break;
		case NEW_ELEMENT:
			if(!e.getSource().equals(this)) {
				ManagerEvent(e);
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof ContainsElementControl) {
				ContainsElement containsElement = ContainsElementControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.CONTAINS_ELEMENT).equals(containsElement)) {
					containsElementManager = new ContainsElementParent(containsElement, this);
					addChildManager(containsElementManager);
				}
			} else if(e.getSource() instanceof ElementControl) {
				Element containsElement = ElementControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.ELEMENT).equals(containsElement)) {
					elementManager = new ElementParent(containsElement, this);
					addChildManager(elementManager);
				}
			}
			break;
		default:
			break;
		}
	}
}
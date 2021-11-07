package org.httprobot.placeholder.html;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Manager;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.unit.IsInstance;
import org.httprobot.unit.IsInstanceControl;
import org.httprobot.unit.IsInstanceManager;

import com.gargoylesoftware.htmlunit.html.DomNode;

@XmlRootElement
public final class ElementManager
	extends AbstractHtmlManager<DomNode, Set<DomNode>, ElementControl> {

	/**
	 * 8813593464366243836L
	 */
	private static final long serialVersionUID = 8813593464366243836L;
	
	IsInstanceManager isInstanceManager;
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
	public ElementManager(Element message, Manager<?> parent) {
		super(message, ElementControl.class, parent);
	}
	@Override
	public Set<DomNode> put(DomNode key, Set<DomNode> value) {
		keySet().add(key);
		if(isInstanceManager != null) {
			List<Object> result = key.getByXPath(getControl().getMessage().getXPath());
			for(Object object : result) {
				isInstanceManager.put(object, null);
				if(isInstanceManager.getValue()) {
					DomNode output = (DomNode) isInstanceManager.getKey();
					value.add(output);
					if(containsElementManager != null) {
						containsElementManager.put(output, null);
						if(containsElementManager.getValue()) {
							if(elementManager != null) {
								elementManager.put(output, new LinkedHashSet<DomNode>());
							}
						}
					} else if(elementManager != null) {
						elementManager.put(output, new LinkedHashSet<DomNode>());
					}
				}
			}
		}
		return super.put(key, value);
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case IS_INSTANCE_CONTROL_LOADED:
			if(e.getSource() instanceof IsInstanceControl) {
				IsInstance isInstance = IsInstanceControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.IS_INSTANCE).equals(isInstance)) {
					isInstanceManager = new IsInstanceManager(isInstance, this);
					addChildManager(isInstanceManager);
				}
			}
			break;
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
			
			break;
		default:
			break;
		}
	}
}
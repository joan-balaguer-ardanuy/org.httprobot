package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.html.Element;

@XmlRootElement
public final class ElementControl 
	extends AbstractHtmlControl {

	/**
	 * -1055172865009492574L
	 */
	private static final long serialVersionUID = -1055172865009492574L;
	
	ElementControl elementControl;
	ContainsElementControl containsElementControl;
	
	@Override
	@XmlElement
	public Element getMessage() {
		return (Element) super.getMessage();
	}
	public ElementControl() {
		super();
	}
	public ElementControl(Element message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				Element element = Element.class.cast(e.getValue());
				if (element.getContainsElement() != null) {
					new ContainsElementControl(element.getContainsElement(), this);
				}
				if (element.getElement() != null) {
					new ElementControl(element.getElement(), this);
				}
				if(element.getClick() == null) {
					throw new Error("ElementControl.OnEventReceived: Click property is missing.");
				}
				if(element.getStore() == null) {
					throw new Error("ElementControl.OnEventReceived: Save property is missing.");
				}
			} else if (e.getSource() instanceof ElementControl) {
				elementControl = ElementControl.class.cast(e.getSource());
				addChild(elementControl);
			} else if (e.getSource() instanceof ContainsElementControl) {
				containsElementControl = ContainsElementControl.class.cast(e.getSource());
				addChild(containsElementControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				Element element = Element.class.cast(e.getValue());
				put(Data.XPATH, element.getXPath());
				put(Data.CLICK, element.getClick());
				put(Data.SAVE, element.getStore());
				put(Data.JAVASCRIPT, element.getJavaScript());
			}
			break;
		default:
			break;
		}
	}
}
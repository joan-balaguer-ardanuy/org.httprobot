package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.placeholder.html.AbstractHtmlControl;
import org.httprobot.placeholder.html.Element;

@XmlRootElement
public final class ElementControl 
	extends AbstractHtmlControl<Element> {

	/**
	 * -1055172865009492574L
	 */
	private static final long serialVersionUID = -1055172865009492574L;
	
	@Override
	@XmlElement
	public Element getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Element message) {
		super.setMessage(message);
	}
	
	public ElementControl() {
		super();
	}
	public ElementControl(Element message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {
			Element element = Element.class.cast(e.getMessage());
			if (element.getXPath() == null) {
				throw new Error("ElementControl.OnControlInitialized: XPath expression is missing.");
			}
			if(element.getContainsElement() != null) {
				new ContainsElementControl(element.getContainsElement(), this);
			}
			if(element.getElement() != null) {
				new ElementControl(element.getElement(), this);
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {
			Element element = Element.class.cast(e.getMessage());
			put(Data.XPATH, element.getXPath());
			put(Data.CLICK, element.getClick());
			put(Data.JAVASCRIPT, element.getJavaScript());
			
			reset();
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.ELEMENT_CONTROL_LOADED));
		}
	}
}
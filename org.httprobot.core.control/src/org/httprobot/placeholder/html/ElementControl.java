package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ElementControl 
	extends AbstractHtmlControl<Element> {

	/**
	 * -1055172865009492574L
	 */
	private static final long serialVersionUID = -1055172865009492574L;
	
	ElementControl elementControl;
	ContainsElementControl containsElementControl;
	
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
			put(Data.ADD, element.getStore());
			put(Data.JAVASCRIPT, element.getJavaScript());
			
			//Check if control has child XML message controls
			if(hasChildControls())
			{
				//Iterate through child XML message controls
				while(hasNext())
				{
					//Get next child XML message control
					ControlListener control = next();
					
					if(control instanceof ElementControl ?
							elementControl.equals(control) : false) {
						elementControl.loadControl();
					} else if(control instanceof ContainsElementControl ?
							containsElementControl.equals(control) : false) {
						containsElementControl.loadControl();
					}
				}
			}
			reset();
			// Send event to parent
			CommandListenerEvent(new CommandEventArgs(this, Command.ELEMENT_CONTROL_LOADED));
		}
	}
}
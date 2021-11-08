package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.unit.IsInstanceControl;

@XmlRootElement
public final class ElementControl 
	extends AbstractHtmlControl<Element> {

	/**
	 * -1055172865009492574L
	 */
	private static final long serialVersionUID = -1055172865009492574L;
	
	IsInstanceControl isInstanceControl;
	ContainsElementControl containsElementControl;
	ElementControl elementControl;
	
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
			if (element.getNodeName() == null ? 
					element.getXPath() == null ? 
							element.getTagName() == null
							: false : false) {
				throw new Error("ElementControl.OnControlInitialized: XML message is inconsistent.");
			}
			if(element.getElement() != null) {
				new ElementControl(element.getElement(), this);
			}
			if(element.getContainsElement() != null) {
				new ContainsElementControl(element.getContainsElement(), this);
			}
			if(element.getIsInstance() != null) {
				new IsInstanceControl(element.getIsInstance(), this);
			}
		} else if(e.getSource() instanceof IsInstanceControl) {
			isInstanceControl = IsInstanceControl.class.cast(e.getSource());
			addChildControl(isInstanceControl);
		} else if(e.getSource() instanceof ContainsElementControl) {
			containsElementControl = ContainsElementControl.class.cast(e.getSource());
			addChildControl(containsElementControl);
		} else if(e.getSource() instanceof ElementControl) {
			elementControl = ElementControl.class.cast(e.getSource());
			addChildControl(elementControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {
			Element element = Element.class.cast(e.getMessage());
			put(Data.NODE_NAME, element.getNodeName());
			put(Data.XPATH, element.getXPath());
			put(Data.TAG_NAME, element.getTagName());
			
			//Check if control has child XML message controls
			if(hasChildControls())
			{
				//Iterate through child XML message controls
				while(hasNext())
				{
					//Get next child XML message control
					ControlListener control = next();
					
					if(control instanceof IsInstanceControl ?
							isInstanceControl.equals(control) : false) {
						isInstanceControl.loadControl();
					} else if(control instanceof ElementControl ?
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
			CommandLineEvent(new CommandEventArgs(this, Command.ELEMENT_CONTROL_LOADED));
		}
	}
}
package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ElementControl 
	extends AbstractHtmlControl<Element> {

	/**
	 * -1055172865009492574L
	 */
	private static final long serialVersionUID = -1055172865009492574L;
	
	public ElementControl() {
		super();
		setMessage(new Element());
	}
	public ElementControl(Element message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {
			Element element = Element.class.cast(e.getMessage());
			if (element.getNodeName() == null) {
				throw new Error("ElementControl.OnControlInitialized: NodeName XML element message is missing.");
			}
			if (element.getXPath() == null) {
				throw new Error("ElementControl.OnControlInitialized: XPath XML element message is missing.");
			}
			if (element.getTagName() == null) {
				throw new Error("ElementControl.OnControlInitialized: TagName XML element message is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {
			Element element = Element.class.cast(e.getMessage());
			if (element.getNodeName() != null) {
				put(Data.NODE_NAME, element.getNodeName());
			}
			if (element.getXPath() == null) {
				put(Data.XPATH, element.getXPath());
			}
			if (element.getTagName() == null) {
				put(Data.TAG_NAME, element.getTagName());
			}
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.ELEMENT_CONTROL_LOADED));
		}
	}
}
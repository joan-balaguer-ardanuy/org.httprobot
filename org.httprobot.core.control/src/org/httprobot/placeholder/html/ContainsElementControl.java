package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.html.ContainsElement;

@XmlRootElement
public final class ContainsElementControl 
	extends AbstractHtmlControl<ContainsElement> {

	/**
	 * -6845601402472213527L
	 */
	private static final long serialVersionUID = -6845601402472213527L;
	
	@Override
	@XmlElement
	public ContainsElement getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(ContainsElement message) {
		super.setMessage(message);
	}
	
	public ContainsElementControl() {
		super();
	}
	public ContainsElementControl(ContainsElement message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {
			ContainsElement element = ContainsElement.class.cast(e.getMessage());
			if (element.getXPath() == null) {
				throw new Error("ContainsElementControl.OnControlInitialized: XPath expression missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			ContainsElement element = ContainsElement.class.cast(e.getMessage());
			put(Data.XPATH, element.getXPath());
			// Send event to parent
			SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
	}
}